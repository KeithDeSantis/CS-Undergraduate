#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "project2.h"



/* ***************************************************************************
 ALTERNATING BIT AND READY-BACK-N NETWORK EMULATOR: VERSION 1.1  J.F.Kurose

   This code should be used for unidirectional or bidirectional
   data transfer protocols from A to B and B to A.
   Network properties:
   - one way network delay averages five time units (longer if there
     are other messages in the channel for GBN), but can be larger
   - packets can be corrupted (either the header or the data portion)
     or lost, according to user-defined probabilities
   - packets may be delivered out of order.

   Compile as gcc -g project2.c student2.c -o p2
**********************************************************************/



/********* STUDENTS WRITE THE NEXT SEVEN ROUTINES *********/
/* 
 * The routines you will write are detailed below. As noted above, 
 * such procedures in real-life would be part of the operating system, 
 * and would be called by other procedures in the operating system.  
 * All these routines are in layer 4.
 */

/* 
 * A_output(message), where message is a structure of type msg, containing 
 * data to be sent to the B-side. This routine will be called whenever the 
 * upper layer at the sending side (A) has a message to send. It is the job 
 * of your protocol to insure that the data in such a message is delivered 
 * in-order, and correctly, to the receiving side upper layer.
 */


/* Defines */
#define WAIT (1)
#define READY (0)

/* Variables */
extern int TraceLevel;
int sendState;
struct pkt sendPkt;
struct pkt recvACK;
struct Node {
    char data[20];
    struct Node* next;
};
struct Node* head = NULL;
struct Node* tail = NULL;
int recvLastSeqNum;


/* Calculate the checkSum of the given packet */
unsigned short calcCheck(struct pkt Packet)
{
    Packet.checksum = 0;
    char arr[sizeof(Packet)];
    memcpy(arr, &Packet, sizeof(Packet));
    char* buff = arr;
    int size = sizeof(arr);
    unsigned short one = 0;
    unsigned short two;
    int x;
    int y;
    for (x = 0; x < size; x++) {
        two = buff[x]<< 8;
        for (y = 0; y < 8; y++) {
            if ((two & 0x8000) ^ (one & 0x8000)) {
                one = (one <<= 1) ^ 4129;
            }
            else {
                one <<= 1;
            }
            two <<= 1;
        }
    }
    return (int) one;
}


/* pop off of queue*/
char *pop() {
    struct Node* temp = head;
    if(head == NULL) {
        return NULL;
    }
    if(head == tail) {
        head = tail = NULL;
    } else {
        head = head->next;
    }
    return temp->data;
}

/* push on to queue*/
void push(char *data) {
    struct Node* temp = (struct Node*)malloc(sizeof(struct Node));
    strncpy(temp->data, data, 20);
    temp->next = NULL;
    if(head == NULL && tail == NULL){
        head = tail = temp;
        return;
    }
    tail->next = temp;
    tail = temp;
}

/* Check if a packet is corrupt */
int checkCorrupt(struct pkt packet) {
    if(packet.acknum != 0 && packet.acknum != 1) {
        if(TraceLevel > 1) {
            printf("Corrupted Packet Acknum: %d;\n\n", sendPkt.acknum);
        }
        return 0;
    }
    if(packet.seqnum != 0 && packet.seqnum != 1) {
        if(TraceLevel > 1) {
            printf("Corrupted Packet Sequence #: %d;\n\n", sendPkt.seqnum);
        }
        return 0;
    }
    if(calcCheck(packet) != packet.checksum) {
        if(TraceLevel > 1) {
            printf("Corrupted Packet Checksum\n Packet Checksum:%d\n SendPkt checksum: %d;\n\n", calcCheck(packet), sendPkt.checksum);
        }
        return 0;
    }
    return 1;
}

void A_output(struct msg message) {

    /* Packet ready to send? */
    if(sendState == WAIT) {
        push(message.data);
        return;
    }
    else if(sendState == READY)
    {

        /* transfer message to packet */
        sendPkt.seqnum = (sendPkt.seqnum == 0) ? 1 : 0;
        sendPkt.acknum = 0;
        strncpy(sendPkt.payload, message.data, MESSAGE_LENGTH);
        sendPkt.checksum = calcCheck(sendPkt);
        if(TraceLevel > 1)
        {
            printf("A_output: Packet Created.\n Sequence #: %d; \n Acknum: %d;\n Checksum: %d;\n\n", sendPkt.seqnum, sendPkt.acknum, sendPkt.checksum);
        }

        /* Send packet to layer 3*/
        stopTimer(0);
        startTimer(0, 5000);
        tolayer3(0, sendPkt);
        sendState = WAIT;
    }
}

/*
 * Just like A_output, but residing on the B side.  USED only when the
 * implementation is bi-directional.
 */
void B_output(struct msg message)
{
    /* N/A */
}

/*
 * A_input(packet), where packet is a structure of type pkt. This routine
 * will be called whenever a packet sent from the B-side (i.e., as a result
 * of a tolayer3() being done by a B-side procedure) arrives at the A-side.
 * packet is the (possibly corrupted) packet sent from the B-side.
 */
void A_input(struct pkt packet) {
    char empty[MESSAGE_LENGTH];
    memset(empty, '\0', sizeof(empty));
    if((!strcmp(packet.payload, empty)) && (packet.checksum == calcCheck(packet)) && (packet.acknum == 1 && packet.seqnum == sendPkt.seqnum))
    {
        sendState = READY;
        char *text = pop();
        if(text != NULL)
        {
            struct msg newMess;
            strncpy(newMess.data, text, 20);
            A_output(newMess);
        } else {
            if(TraceLevel > 1)
            {
                printf("A_input: Packet did not send.");
            }
        }
    }
}

/*
 * B_input(packet),where packet is a structure of type pkt. This routine
 * will be called whenever a packet sent from the A-side (i.e., as a result
 * of a tolayer3() being done by a A-side procedure) arrives at the B-side.
 * packet is the (possibly corrupted) packet sent from the A-side.
 */
void B_input(struct pkt packet) {
    if(checkCorrupt(packet) == 0)
    {
        recvACK.seqnum = recvLastSeqNum;
        recvACK.checksum = calcCheck(recvACK);
        tolayer3(1, recvACK);
        return;
    } else if(packet.seqnum == recvLastSeqNum)
    {
        recvACK.seqnum = recvLastSeqNum;
        recvACK.checksum = calcCheck(recvACK);
        tolayer3(1, recvACK);
    } else {
        recvLastSeqNum = packet.seqnum;
        recvACK.seqnum = packet.seqnum;
        recvACK.checksum = calcCheck(recvACK);
        tolayer3(1, recvACK);
        struct msg message;
        strncpy(message.data, packet.payload, 20);
        tolayer5(1, message);
    }
}

/*
 * A_timerinterrupt()  This routine will be called when A's timer expires
 * (thus generating a timer interrupt). You'll probably want to use this
 * routine to control the retransmission of packets. See starttimer()
 * and stoptimer() in the writeup for how the timer is started and stopped.
 */
void A_timerinterrupt() {
    stopTimer(0);
    sendState = READY;
    struct msg message;
    strncpy(message.data, sendPkt.payload, 20);
    sendPkt.seqnum = (sendPkt.seqnum == 0) ? 1 : 0; // returns 0 or 1
    A_output(message);
}

/*
 * B_timerinterrupt()  This routine will be called when B's timer expires
 * (thus generating a timer interrupt). You'll probably want to use this
 * routine to control the retransmission of packets. See starttimer()
 * and stoptimer() in the writeup for how the timer is started and stopped.
 */
void  B_timerinterrupt() {
    /* N/A */
}


/* The following routine will be called once (only) before any other    */
/* entity A routines are called. You can use it to do any initialization */
void A_init() {
    sendPkt.seqnum = 0;
    sendPkt.acknum = 1;
    sendState = READY;
}

/*
 * The following routine will be called once (only) before any other
 * entity B routines are called. You can use it to do any initialization
 */
void B_init() {
    recvLastSeqNum = 0;
    recvACK.acknum = 1;
    memset(recvACK.payload, '\0', sizeof(recvACK.payload));
}
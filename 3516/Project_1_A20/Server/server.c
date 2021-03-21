#include <stdio.h> /* for printf() and fprintf() */
#include <sys/socket.h> /* for socket(), bind(), and connect() */
#include <arpa/inet.h> /* for sockaddr_in and inet_ntoa() */
#include <stdlib.h> /* for atoi() and exit() */
#include <string.h> /* for memset() */
#include <unistd.h> /* for close() */


#define MAXPENDING 5 /* Maximum outstanding connection requests */
#define RCVBUFSIZE 500 /* Size of receive buffer */
#define SENDBUFFER 500
void DieWithError(char* errorMessage){
    puts(errorMessage);
    exit(1);
}; /* Error handling function */
void HandleTCPClient(int clntSocket){
    char* ok = "HTTP/1.1 200 OK\r\n\r\n" ;
    FILE* TMDG = fopen("TMDG.html","r");
    char buff[SENDBUFFER];
    char text[SENDBUFFER];

    int bytesSent;
    int totalBytesRcvd = 0;
    char echoBuffer[RCVBUFSIZE];

    int bytesRcvd = recv(clntSocket,echoBuffer,RCVBUFSIZE-1,0);
    if(bytesRcvd<= 0){
        DieWithError("recv() failed");
    }
    totalBytesRcvd += bytesRcvd;
    echoBuffer[bytesRcvd] = '\0';
    send(clntSocket,ok,strlen(ok),0);
    do{

        bytesSent= fscanf(TMDG, "%500c", buff);
        send(clntSocket,(char*)buff,strlen(buff),0);

    }while (strstr(buff,"</html>")==NULL);

    puts("DONE");
    fclose(TMDG);
} /* TCP client handling function */

int main(int argc, char *argv[])
{ int servSock; /*Socket descriptor for server */
    int clntSock; /* Socket descriptor for client */
    struct sockaddr_in echoServAddr; /* Local address */
    struct sockaddr_in echoClntAddr; /* Client address */
    unsigned short echoServPort; /* Server port */
    unsigned int clntLen; /* Length of client address data structure */
    if (argc != 2) /* Test for correct number of arguments */
    {
        fprintf(stderr, "Usage: %s <Server Port>\n", argv[0]);
        exit(1);
    }
    echoServPort = atoi(argv[1]); /* First arg: local port */
/* Create socket for incoming connections */
    if ((servSock = socket (AF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
        DieWithError("socket() failed");
    /* Construct local address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr)); /* Zero out structure */
    echoServAddr.sin_family = AF_INET; /* Internet address family */
    echoServAddr.sin_addr.s_addr = htonl(INADDR_ANY); /* Any incoming interface */
    echoServAddr.sin_port = htons(echoServPort); /* Local port */
/* Bind to the local address */
    if (bind (servSock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr))<0){
        DieWithError("bind() failed");
        close(servSock);
    }

/* Mark the socket so it will listen for incoming connections */
    if (listen (servSock, MAXPENDING) < 0){
        DieWithError("listen() failed");
        close(servSock);

    }
    puts("Waiting for connection");

    for (;;) /* Run forever */
    {
/* Set the size of the in-out parameter */
        clntLen = sizeof(echoClntAddr); /* Wait for a client to connect */
        if ((clntSock = accept (servSock, (struct sockaddr *) &echoClntAddr, &clntLen)) < 0){
            DieWithError("accept() failed");
            close(servSock);
        }

/* clntSock is connected to a client! */
        printf("Handling client %s\n", inet_ntoa(echoClntAddr.sin_addr));
        HandleTCPClient(clntSock);
    }
/* NOT REACHED */

}


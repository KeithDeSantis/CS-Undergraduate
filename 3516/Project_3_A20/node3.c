#include <stdio.h>
#include "project3.h"

extern int TraceLevel;
extern float clocktime;
/*
 * 3-0 4
 * 3-1 3
 * 3-2 2
 * 3-3 0
 *
 *
 * 0---1---1
 * |\      |
 * | \     |
 * |  \    |
 * 7   3   1
 * |    \  |
 * |     \ |
 * |      \|
 * 3---2---2
 */

#define NODE_ID 3
struct distance_table {
  int costs[MAX_NODES][MAX_NODES];
};
void sendPkt3();
void updateDT3(int, int[]);
struct distance_table dt3;
struct NeighborCosts   *neighbor3;
void printdt3(int MyNodeNumber, struct NeighborCosts *neighbor,
              struct distance_table *dtptr);
/* students to write the following two routines, and maybe some others */

void rtinit3() {
    printf("At time t=%f, rtinit%d() called.\n", clocktime,NODE_ID);
    for (int i = 0; i < MAX_NODES; i++) {
        for (int j = 0; j < MAX_NODES; j++) {
            dt3.costs[i][j]=9999;
            if(i==j){
                dt3.costs[i][j]=0;
            }
        }
    }
    neighbor3 = getNeighborCosts(NODE_ID);
    printf("At time t=%f, node %d initial distance vector:",clocktime,NODE_ID);
    for (int i = 0; i < MAX_NODES; i++) {
        printf(" %d ",neighbor3->NodeCosts[i]);
    }
    printf("\n");
    updateDT3(NODE_ID,neighbor3->NodeCosts);
    sendPkt3(neighbor3->NodeCosts);
    if(TraceLevel > 1){
        printdt3(NODE_ID,neighbor3,&dt3);
    }

}

void sendPkt3(int cost[MAX_NODES]) {
    struct RoutePacket rtpkt;
    rtpkt.sourceid = NODE_ID;
    printf("At time t=%f, node %d current distance vector:",clocktime,NODE_ID);
    for (int j = 0; j < MAX_NODES; j++) {
        printf(" %d ", cost[j]);
    }
    printf("\n");
    for (int i = 0; i < MAX_NODES; i++) {
        if(i != NODE_ID){
            if(cost[i]<9999){
                rtpkt.destid = i;
                for (int j = 0; j < MAX_NODES; j++) {
                    rtpkt.mincost[j]=cost[j];
                }

                printf("At time t=%f, node %d sends packet to node %d with:",clocktime,NODE_ID,rtpkt.destid);
                for (int j = 0; j < MAX_NODES; j++) {
                    printf(" %d ",rtpkt.mincost[j]);
                }
                printf("\n");
                toLayer2(rtpkt);
            }
        }
    }
}

void updateDT3(int node, int row[MAX_NODES]){
    for (int i = 0; i < MAX_NODES; i++) {
        for (int j = 0; j < MAX_NODES; j++) {
            if(i==node){
                dt3.costs[i][j]=row[j];
            } else if(j==node){
                dt3.costs[i][j]=row[i];
            }
        }
    }
}

void rtupdate3( struct RoutePacket *rcvdpkt ) {
    printf("At time t=%f, rtupdate%d() called, by a pkt received from Sender id: %d\n",clocktime, NODE_ID,rcvdpkt->sourceid);
    int node = rcvdpkt->sourceid;
    updateDT3(node, rcvdpkt->mincost);
    for (int i = 0; i < MAX_NODES; i++) {
        for (int j = 0; j < MAX_NODES; j++) {
            if(i!=NODE_ID&& i == node){
                int costToPivot = neighbor3->NodeCosts[i];
                int pivotToDst = rcvdpkt->mincost[j];
                int costToDst = neighbor3->NodeCosts[j];
                if(costToPivot+pivotToDst<costToDst){
                    neighbor3->NodeCosts[j]=costToPivot+pivotToDst;
                    sendPkt3(neighbor3->NodeCosts);
                }
            }
        }
    }

    if (TraceLevel > 1){
        printdt3(NODE_ID,neighbor3,&dt3);
    }

}


/////////////////////////////////////////////////////////////////////
//  printdt
//  This routine is being supplied to you.  It is the same code in
//  each node and is tailored based on the input arguments.
//  Required arguments:
//  MyNodeNumber:  This routine assumes that you know your node
//                 number and supply it when making this call.
//  struct NeighborCosts *neighbor:  A pointer to the structure 
//                 that's supplied via a call to getNeighborCosts().
//                 It tells this print routine the configuration
//                 of nodes surrounding the node we're working on.
//  struct distance_table *dtptr: This is the running record of the
//                 current costs as seen by this node.  It is 
//                 constantly updated as the node gets new
//                 messages from other nodes.
/////////////////////////////////////////////////////////////////////
void printdt3( int MyNodeNumber, struct NeighborCosts *neighbor, 
		struct distance_table *dtptr ) {
    int       i, j;
    int       TotalNodes = neighbor->NodesInNetwork;     // Total nodes in network
    int       NumberOfNeighbors = 0;                     // How many neighbors
    int       Neighbors[MAX_NODES];                      // Who are the neighbors

    // Determine our neighbors 
    for ( i = 0; i < TotalNodes; i++ )  {
        if (( neighbor->NodeCosts[i] != INFINITY ) && i != MyNodeNumber )  {
            Neighbors[NumberOfNeighbors] = i;
            NumberOfNeighbors++;
        }
    }
    // Print the header
    printf("                via     \n");
    printf("   D%d |", MyNodeNumber );
    for ( i = 0; i < NumberOfNeighbors; i++ )
        printf("     %d", Neighbors[i]);
    printf("\n");
    printf("  ----|-------------------------------\n");

    // For each node, print the cost by travelling thru each of our neighbors
    for ( i = 0; i < TotalNodes; i++ )   {
        if ( i != MyNodeNumber )  {
            printf("dest %d|", i );
            for ( j = 0; j < NumberOfNeighbors; j++ )  {
                    printf( "  %4d", dtptr->costs[i][Neighbors[j]] );
            }
            printf("\n");
        }
    }
    printf("\n");
}    // End of printdt3


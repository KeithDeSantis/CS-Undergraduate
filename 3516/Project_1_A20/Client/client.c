#include <stdio.h> /* for printf() and fprintf() */
#include <sys/socket.h> /* for socket(), connect(), send(), and recv() */
#include <arpa/inet.h> /* for sockaddr_in and inet_addr() */
#include <stdlib.h> /* for atoi() and exit() */
#include <string.h> /* for memset() */
#include <unistd.h> /* for close() */
#include <stdbool.h>
#include <netdb.h>
#include <sys/time.h>

#define RCVBUFSIZE 500 /* Size of receive buffer */

void DieWithError(char *errorMessage){
fprintf(stderr,"%s\n",errorMessage);
exit(1);
} /* Error handling function */

int main(int argc, char *argv[])
{
    fflush(stdout);
    FILE* index = fopen("index.html","w+");
    bool write = false;
    int sock; /* Socket descriptor */
    struct sockaddr_in ServAddr; /* http server address */
    struct addrinfo hints;
    struct addrinfo* res;
    struct timeval start, stop;
    char* ServPort; /* http server port */
    char* servIP; /* Server IP address (dotted quad) */
    char* get = "GET / HTTP/1.1\r\n"; /* String to send to http server */
    char* host = "Host: ";
    char httpBuffer[RCVBUFSIZE]; /* Buffer for http string */
    char* hostname;
    char* connectionAlive = "\r\nConnection: keep-alive\r\n";
    unsigned int httpGetLen; /* Length of string to http */
    int bytesRcvd, totalBytesRcvd; /* Bytes read in single recv() and total bytes read */
    bool options = false;


    if ((argc < 3) || (argc > 4)) /* Test for correct number of arguments */
    {
        fprintf(stderr, "Usage: %s [-options] server_url port_number\n",
                argv[0]);
        exit(1);
    }
    if (argc == 4) {
        ServPort = argv[3]; /* Use given port, if any */
        options = true;
        if(strcmp(argv[1],"-p")!=0){
            DieWithError("Wrong option");
        }
        hostname = (char*) argv[2];
    }else {
        hostname = (char*) argv[1];
        ServPort = (char*) argv[2];
    }
    char *httpGet = malloc(strlen(get)+strlen(hostname)+strlen(connectionAlive)+10);
    strcpy(httpGet,get);
    strcat(httpGet,host);
    strcat(httpGet,hostname);
    strcat(httpGet,connectionAlive);
    strcat(httpGet,"\r\n");


    printf("%s:%s\n",hostname,ServPort);

    memset(&hints, 0, sizeof hints);
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_STREAM;
    int rv;
    if ( (rv = getaddrinfo(hostname , ServPort , &hints , &res)) != 0)
    {
        fprintf(stderr, "getaddrinfo: %s\n", gai_strerror(rv));
        exit(1);
    }


    if ((sock = socket(res->ai_family, res->ai_socktype, res->ai_protocol)) < 0){
        DieWithError("socket() failed");
    }
        
    gettimeofday(&start,NULL);
    if (connect(sock, res->ai_addr, res->ai_addrlen) == -1){
        DieWithError("connect() failed");
    }  
    gettimeofday(&stop,NULL);
    httpGetLen = strlen(httpGet); /* Determine input length */
/* Send the string to the server */

    unsigned int sent = send (sock, httpGet, httpGetLen, 0);
    printf("Sent: %s\n", httpGet);
    if ( sent != httpGetLen){ 
        DieWithError("send() sent a different number of bytes than expected");
    }
/* Receive the same string back from the server */
    totalBytesRcvd = 0;
    printf("\n\n\nReceived. Check index.html \n");
    do {
        bytesRcvd = recv(sock, httpBuffer, RCVBUFSIZE - 1, 0);

        if (bytesRcvd <= 0) {
            DieWithError("recv() failed or connection closed prematurely");
        }
        httpBuffer[bytesRcvd] = '\0'; 
        totalBytesRcvd += bytesRcvd; 
        fprintf(index,"%s",httpBuffer);

    }
    while (bytesRcvd <= RCVBUFSIZE-1 && strstr(httpBuffer,"</html>")==NULL);


    printf("\n"); 
    close (sock);
    fclose(index);
    if(options){
        printf("RTT: %f\n",((float)(stop.tv_usec-start.tv_usec) + (float)((stop.tv_sec-start.tv_sec)*1000000))/1000);
    }
    exit(0);
}


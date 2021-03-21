/*
 * Authors:
 * Kush Shah kshah2@wpi.edu
 * Keith DeSantis kwdesantis.wpi.edu
 * Project3 CS3015
 * March 1st 2021
 */

#include <stdio.h>
#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <sys/mman.h>
#include <time.h>
#include <unistd.h>
#include "stddef.h"
#include "goatmalloc.h"
#include "math.h"
#define TRUE 1
#define FALSE 0

int totalSize = 0;
node_t *front;

int statusno = 0;

extern int init(size_t size){
    printf("Initializing arena:\n");
    printf("\t-requested size is %zu bytes\n",size);
    printf("\t-pagesize is %d bytes\n",getpagesize());
    printf("\t-adjusting size with page boundaries\n");
    if(size > MAX_ARENA_SIZE){
        printf("\t-error: requested size larger than MAX_ARENA_SIZE (%d)\n",MAX_ARENA_SIZE);
        return ERR_BAD_ARGUMENTS;
    }
    double sizeOverPage = size/(double) getpagesize();
    if(size % getpagesize()) (sizeOverPage++) ;
    size = (int)sizeOverPage * getpagesize();
    totalSize = size;
    printf("\t-adjusted size is %d bytes\n",size);
    printf("\t-mapping arena with mmap()\n");
    int fd=open("/dev/zero",O_RDWR);
    void *_arena_start = mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_PRIVATE, fd, 0);
    if(_arena_start == MAP_FAILED){
        printf("\t-error: ERR_SYSCALL_FAILED\n");
        return ERR_SYSCALL_FAILED;
    }
    printf("\t-arena starts at 0x%x\n",(void *) _arena_start);
    printf("\t-arena ends at 0x%x\n",(void *)_arena_start + (int)size);
    printf("\t-initializing header for initial free chunk\n");
    front = _arena_start;
    front->size = size - sizeof(node_t);
    front->bwd = NULL;
    front->fwd = NULL;
    front->is_free = TRUE;
    printf("\t-header size is %lu\n",sizeof(node_t));
    return size;
}
extern int destroy(){
    printf("Destroying Arena:\n"
           "\t-unmapping arena with munmap()\n");
    int error = munmap(front, totalSize);
    if(error){
        printf("\t-error: ERR_UNINITIALIZED\n");
        return ERR_UNINITIALIZED;
    }
    statusno = 0;
    totalSize = 0;
    front = NULL;
    return 0;
}

extern void* walloc(size_t size){
    if(front == NULL){
        printf("\t-error: Uninitialized. Setting status code\n");
        statusno = ERR_UNINITIALIZED;
        return NULL;
    }
    printf("Allocating Memory:\n");
    printf("\t-looking for free chunk of >= %zu bytes\n",size);
    node_t *new = front;
    do {
        if(new->size >= size && new->is_free){
            printf("\t-found free chunk of %zu bytes with header at 0x%x\n",new->size,(void *)new);
            printf("\t-free chunk->fwd currently points to (nil)\n");
            printf("\t-free chunk->bwd currently points to (nil)\n");
            printf("\t-checking if splitting is required\n");
            if(size < new->size && new->size - size >= sizeof(node_t)){
                printf("\t-splitting free chunk\n");
                node_t *free = ((void *)new + sizeof(node_t) + size);
                free->size = new->size - size - sizeof(node_t);
                free->is_free = TRUE;
                free->fwd = new->fwd;
                free->bwd = new;
                if(new->fwd){
                    new->fwd->bwd = free;
                }

                new->fwd = free;
            }else{
                printf("\t-splitting is not required\n");
            }
            printf("\t-updating chunk header at 0x%lx\n",(void *)new);
            if(new->size - size >= sizeof(node_t)) {
                new->size = size;
            }
            printf("\t-allocation starts at 0x%lx\n",(void *)new + sizeof(node_t));
            new->is_free = 0;
            return (void *)new + sizeof(node_t);
        }
        new = new->fwd;
    }while(new);
    printf("\t-error:no such free chunk exists\n"
           "\t-setting error code\n");
    statusno = ERR_OUT_OF_MEMORY;
    return NULL;
}

extern void wfree(void *ptr){
    printf("Freeing allocated memory:\n");
    printf("\t-supplied pointer 0x%x\n",(void *) ptr);
    printf("\t-accessing chunk header at 0x%x\n",(void *) ptr-sizeof(node_t));

    node_t *start = ptr - sizeof(node_t);
    node_t *end = ptr - sizeof(node_t);
    printf("\t-chunk size is %zu\n",start->size);
    int foundStart = 0;
    int foundEnd = 0;
    printf("\t-checking if coalescing is needed\n");
    do {
        if(!foundStart) {
            if (start->bwd==NULL || !start->bwd->is_free) {
                foundStart = TRUE;
            } else {
                start = start->bwd;
            }
        }
        if(!foundEnd ){
            if(end->fwd == NULL || !end->fwd->is_free){
                foundEnd = TRUE;
            }else{
                end = end->fwd;
            }
        }
    }while(!foundStart && !foundEnd);
    if(start != end){
        printf("\t-coalescing is required\n");
    }else{
        printf("\t-coalescing is not required\n");
    }
    start->size = (((void *)end + end->size + sizeof(node_t)) - ((void *)start + sizeof(node_t)));
    start->is_free = TRUE;
    start->fwd = end->fwd;
    if(end->fwd != NULL){
        end->fwd->bwd = start;
    }
}


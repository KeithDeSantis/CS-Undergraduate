import math


def eat(mat, pfo, pfr):
    print(
        f'\tMemory access time = {mat}\n\tPage fault overhead = {pfo}\n\tPage fault rate = {pfr}')
    eat = (1-pfr) * (mat) + (pfr * (pfo))
    return eat

def main():
    memory_access_time = 2 * pow(10,-7) #200 nano seconds
    page_fault_overhead = 0.015 #15 milliseconds
    page_fault_rate = 2 * pow(10,-3)


    print(f'EAT = {eat(memory_access_time, page_fault_overhead, page_fault_rate)} seconds')

    '''
    The EAT can be improved by increasing the amount of RAM in the system
    or you can upgrade from a HDD to an SSD which will reduce latency and the differential between the memory and the hard disk space
'''

if __name__ == '__main__':
    main()
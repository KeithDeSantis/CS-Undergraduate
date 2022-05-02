#include<stdio.h>

int main() {
  int count = 0;

  // Count will be 4 
  printf("abcd%n", &count);
  printf("\nCount: %d %x\n", count, count);

  // Count should be 4 after this call, as the %n does not
  // count characters from previous calls to printf
  printf("abcd%n", &count);
  printf("\nCount: %d %x\n", count, count);

  //Count 5 because len("hello") is five
  printf("%s%n", "Hello", &count);
  printf("\nCount: %d %x\n", count, count);

  // Count 256, padded with spaces
  printf("%256s%n", "Hello", &count);
  printf("\nCount: %d %x\n", count, count);
 
  // Count 0, hex(256) == 0x0100  
  count = 0;
  printf("%256s%hhn", "Hello", (char *)&count);
  printf("\nCount: %d %x\n", count, count);

  // Count 0, hex(256) == 0x0100  
  // These lines behave the same as the example above,
  // but gcc will throw up warnings
  //count = 0;
  //printf("%256s%hhn", "Hello", &count);
  //printf("\nCount: %d %x\n", count, count);

  // Count: 16776960 ffff00 
  count = 0xffffff;
  printf("%256s%hhn", "Hello", (char *)&count);
  printf("\nCount: %d %x\n", count, count);

  // Count: 16711935 ff00ff 
  count = 0xffffff;
  printf("%256s%hhn", "Hello", (char *)&count + 1);
  printf("\nCount: %d %x\n", count, count);
}
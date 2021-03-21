#!/bin/bash
echo Turning on stack and heap randomization
echo "Current value of kernel.randomize_va_space = " `cat /proc/sys/kernel/randomize_va_space`
echo 2 | sudo tee /proc/sys/kernel/randomize_va_space
sudo rm -f /etc/sysctl.d/01-disable-aslr.conf
sudo touch /etc/sysctl.d/01-enable-aslr.conf
echo "kernel.randomize_va_space = 2" | sudo tee /etc/sysctl.d/01-enable-aslr.conf
echo "New value of kernel.randomize_va_space = " `cat /proc/sys/kernel/randomize_va_space`


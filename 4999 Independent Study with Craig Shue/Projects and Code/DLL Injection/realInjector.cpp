#include <iostream>
#include <fstream>
#include <Windows.h>
#include <string>
using namespace std;

int main() 
{
    
    // path to DLL
    LPCSTR DllPath = "";

    // input the PID of the target process (this can be hardcoded as well if you prefer)    
    getline(myfile,line);
    int PID = std::stoi(line);
    
    cout << "Target process ID found: " << PID << endl;
    
    // open a handle to target process
    HANDLE hProcess = OpenProcess(PROCESS_ALL_ACCESS, FALSE, PID);
    
    // allocate memory for the dllpath in the target process 
    // length of dllpath + null terminator
    LPVOID pMemDllPathAlloc = VirtualAllocEx(hProcess, 0, strlen(DllPath) + 1,
        MEM_COMMIT, PAGE_READWRITE);
    
    // Write the path to the address of the memory we just allocated
    // in the target process
     WriteProcessMemory(hProcess, pMemDllPathAlloc, (LPVOID)DllPath, strlen(DllPath) + 1, 0);
    
    DWORD remoteThreadID;
    // Create a remote thread in the target process which
    // calls LoadLibraryA as our dllpath as an argument-> program loads our dll
    HANDLE hLoadThread = CreateRemoteThread(hProcess, 0, 0, 
        (LPTHREAD_START_ROUTINE)GetProcAddress(GetModuleHandleA("Kernel32.dll"), 
        "LoadLibraryA"), pMemDllPathAlloc, 0, &remoteThreadID);
    
    cout << "Remote thread started with PID: " << remoteThreadID << endl;

    // Wait for execution of loader thread to finish
    WaitForSingleObject(hLoadThread, INFINITE);
    
    cout << "Dll path allocated at " << pMemDllPathAlloc << endl;


    // Free the memory allocated for our path
    VirtualFreeEx(hProcess, pMemDllPathAlloc, strlen(DllPath) + 1, MEM_RELEASE); 
    
    return 0;

}

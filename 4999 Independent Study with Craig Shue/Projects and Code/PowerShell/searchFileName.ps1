# attempt at a powershell puzzle, recursively search from the current directory and find
# all files that contain a given substring in their title
# takes args substring, sourceFolder

if(!$args) {
    Write-Output ""
    Write-Output "Please input arguments."
    Write-Output "Proper function call form: ./puzzle.ps1 SUBSTRING SOURCEFOLDER .FILETYPE"
    Write-Output "Where SUBSTRING is the substring being searched for and SOURCEFOLDER is the starting directory."
    Write-Output "You can also include an optional .FILETYPE to sort by only files of the given type."
    Write-Output ""
    return
}

# First input argument, the substring being looked for in file names
$substring = [string]$args[0]
$substring.replace(']','\]') | Out-Null
$substring.replace('[','\[') | Out-Null
# Second input argument, the directory from which to begin the depth first search
$sourceFolder = $args[1]

# If a third argument exists, take it into account
if(!$args[2]) {
    $fileFilter = $FALSE
}
else {
    $fileFilter = $TRUE
    $fileType = [string]$args[2]
}

# The original starting directory, used to return to after all searching is done
$originalDirectory = Get-Location

# Function that takes in a file and prints out the file name and absolute path if
# it contains the desired substring
function containsSymbol([object]$file) {
    $path = $file.FullName
    $name = $file.Name
    $i = $name.IndexOf($substring)
    if ( $i -gt -1 )
    {
        if($fileFilter -and ( [IO.Path]::GetExtension($name) -eq $fileType ) ) {
            Write-Output "Found ----> $name ----> at ----> $path"
            return
        }

        if(!$fileFilter) {
            Write-Output "Found ----> $name ----> at ----> $path"
        }
    }
}

# Recursive function call that performs a depth first search into the directory
function recurFindFiles([object]$source) {
    
    # enter the given directory
    Set-Location $source

    # list items in $source
    Get-ChildItem | ForEach-Object {
        
        # if a file, check if name should be printed
        if (Test-Path -Path $_.FullName -PathType Leaf) {
            containsSymbol($_);
        }

        # if directory, recursively search it
        if (Test-Path -Path $_.FullName -PathType Container) {
            recurFindFiles($_);
            # After recursive search return tp prior dir so we remain in the same working dir
            # that we started in
            Set-Location ..
        }
    }
}

# Call the recusive search on source folder
recurFindFiles($sourceFolder)

# Return the shell to the original directory
Set-Location $originalDirectory


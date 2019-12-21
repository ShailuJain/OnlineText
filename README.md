# OnlineText

This is a command line tool used for doing the following operations on shrib.com (A website to temporarily store some text that you want to store) :

    - Getting the text from the website
    - Writing the text to the website
    - Appending the text to the website

## Steps to download this command line tool:
### Step 1:
Go to dist folder of this repository.
### Step 2:
Download the jar file and the bat file (Optional).
### Step 3:
Set the bat file and jar file in environment variable of windows.

## Syntax for writing the commands for different operations

### For getting the text from the website
    text read <shrib_username> <filename>
    
 Options: -c, --clipboard
 
 These options are used to directly copy the text from website to your clipboard.
 
 Example:
 
    text read -c <shrib_username>
### For writing the text to the website
    text write <shrib_username> <filename>
Options: -c, --clipboard
 
 These options are used to get the text from your clipboard and then write that on to the website.
  
  Example:
  
     text write -c <shrib_username>
### For appending the text to the website
    text append <shrib_username> <filename>
Options: -c, --clipboard
 
 These options are used to get the text from your clipboard and then append that on to the website.
  
  Example:
  
     text append -c <shrib_username>
     
### Help options
Add -h or --help option to any of the command to see how to write the command        
    
    

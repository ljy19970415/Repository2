wc.exe
wc.exe -c -o output.txt
wc.exe -l -w -c -a -s all\*.c -o new.txt -e stop.txt
wc.exe -w -s all\*.c -o new.txt
wc.exe -a test.c -e stop.txt
wc.exe -w test.c
wc.exe -w test.c -e wrong.txt
wc.exe -w wrong.c
wc.exe -c test.c -o
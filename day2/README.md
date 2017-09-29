# Day 2 exercise
### Network 
Please observe that this exercise, marked as “Exam Preparation” is different from most exercises up until now, in that it doesn't involve code. 
You will not get an exam exercise similar to this. But all questions below, could be included as part of a real exam exercise. So make a (Google-Doc) copy of this document, write down your answers, and use them to prepare for the exam. Provide a (read-only) URL to your document to get the studypoint related to this exercise.
Network interfaces, IP-numbers and more
In a terminal run Windows: ipconfig /all       Linux/Unix: sudo ifconfig –a
 Answer the questions below:
1.	What is the ip address of your wireless card?<br>
10.50.131.89
2.	What is special about the IP-addresses that starts with 10 (and 172.16 and 192.168)<br>
They are used for communications in the private local network.
3.	Who or what gave you that address?<br>
DHCP
4.	What is a DHCP server (conceptually)<br>
It dynamically distributes the IPs.
5.	What is the address of your DHCP Server<br>
10.255.128.1
6.	What is a DNS server (conceptually)<br>
Domain Name System: It “translates” the names on the internet over to the IPs of the servers.
7.	What is the DNS server address?<br>
At home: 193.162.153.164
8.	What is a MAC address<br>
Unique physical address on a, for example, wireless adapter
9.	What is the MAC address of the your Network Interface(s)?
74-C6-3B-47-44-8D
10.	How many network interfaces do you have?<br>
3
11.	Why do you have more than one? What are they for?<br>
One is for wireless, one is ethernet, one tunnel thingie, which I have no clue what is.
12.	What is your public address (WAN) (can’t be found with ipconfig)  address right now. Ask others in the class for theirs, do you all have the same public address ?<br>
At home, 87.52.11.123. Most of them have the same.<br>
### Networking
For these exercises you need to use the tools (figure out which ones): ping, netstat, whois, traceroute (windows) or traceroute (linux)
1.	Find the IP address for cphbusiness.dk<br>
195.254.168.52
2.	When was cphbusiness.dk registered first time and whois the Registrant<br>
2008-12-15
Knord S/I
3.	Use ping to verify whether you Digitalocean Droplet is online<br>
IT IS ALIVE!!!
4.	How many routers do you need to go through to reach dr.dk?<br>
7
5.	How many routers do you need to go through to reach rhcloud.com?<br>
16
6.	How many active connections do you have on your computer?<br>
Many.
7.	What is the round-trip time to reach google.com?<br>
Average 18ms
8.	Why is it so low if Google is in the United States?<br>
Because they have servers in Europe.
9.	Start your local Tomcat server and use netstat to verify whether “anyone” is listening on port 8080 and 3306 (what would you expect to find listening on 3306?)<br>
Yes. MySQL is listening on port 3306
10.	If you have setup MySQL on Digital Ocean to be accessible from the school (or home), verify this using Telnet. If not, ask around in the class, and find one who has.<br>
Yesh.<br>
### Domain Name System
For some of these exercises you can use the command line tool nslookup
1.	Find the IP address for your domain name (won’t work, unless you have completed the steps below)<br>
jdbh.dk, IP: 46.101.216.31
2.	Set up your HostName to point to your droplet(s) as explained here in the steps:
Configuring your Domain Part-1,  Change your Domain Server,  Configuring your Domain Part-2
DONE.
3.	Wait some time (why) perhaps an hour, and repeat step 2.<br>
DONE.
4.	Verify that your droplet(s) can be using your domain name<br>
IT CAN!

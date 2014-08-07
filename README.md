JOpenShowVar
============

JOpenShowVar is a Java open-source cross-platform communication interface to Kuka robots that allows for 
reading and writing variables and data structures of the controlled manipulators. This interface, which is
compatible with all Kuka robots that use KR C4 and previous versions, runs as a client on a remote computer 
connected with the Kuka controller via TCP/IP. JOpenShowVar opens up to a variety of possible applications 
making it possible to use different input devices and to develop alternative control methods.

JOpenShowVar may be used to connect to a real KRC controller or a simulated one using the KUKA.OfficeLite package

Video showing a KUKA KR6 R900 sixx being controlled using JOpenShowVar as the communication interface
https://www.youtube.com/watch?v=6aZZAK4oyGg

Usage
=========
KUKAVARPROXY must firstly be started on the KUKA SmartPad (copy paste the folder to somewhere in the WinXP environment -> run KUKAVARPROXY.exe)
Port 7000 has to set open from the SmartPad:
Start-up -> Network configuration -> NAT -> Add port -> Port number 7000 and Permitted protocols: tcp/udp 

In order to successfully establish an connection to the server. Your IP must be assigned a static IP in the same subrange as the one defined in the the SmartPads network configuration.


Example code
===========

```java
public class Example {

	private static String robotIP = "192.168.2.2";  //The static IP of the robot 
	private static int port = 7000;

	public static void main(String[] args) throws IOException {
		CrosscomClient client = new CrossComClient(robotIP, port);  //establish connection
		
		//Reads the current value of $OV_JOG and print the callback containing the value
		Callback readRequest = client.sendRequest(new Request(0, "$OV_JOG")); //read request
		System.out.println(readRequest);
		
		//Set $OV_JOG to 100% and print the callback
		Callback writeRequest = client.sendRequest(new Request(1, "$OV_JOG", "100")); //write request
		System.out.println(writeRequest);
		
		//note that the first int argument can be any number between 0 and 99. It is simply an id given to the request so that it can be tracked. 
	}

}
```

Repository contents
==================
The repository contains 3 projects.
JOpenShowVar-core is the core project and contains the important stuff.
JOpenShowVar-swing provides a GUI interface and references the core project
JOpenShowVar-android is a simple android project that also references the core project

libs contains 3rd party libraries used by the swing project

KUKAVARPROXY contains the files that must be copied over to the WinXP environment on the KUKA SmartPad.

Acknowledgements
==============
A big thanks to Massimiliano Fago, which is the original author of KUKAVARPROXY and the C++ project OpenShowVar (http://sourceforge.net/projects/openshowvar/)
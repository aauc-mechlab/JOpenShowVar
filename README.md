JOpenShowVar
============

JOpenShowVar is a Java open-source cross-platform communication interface to Kuka robots that allows for 
reading and writing variables and data structures of the controlled manipulators. This interface, which is
compatible with all Kuka robots that use KR C4 and previous versions, runs as a client on a remote computer 
connected with the Kuka controller via TCP/IP. JOpenShowVar opens up to a variety of possible applications 
making it possible to use different input devices and to develop alternative control methods.

JOpenShowVar may be used to connect to a real KRC controller or a simulated one using the KUKA.OfficeLite package

Usage
=========
KUKAVARPROXY must firstly be installed on the KUKA  SmartPad (copy paste the folder to somewhere in the windows environment -> run KUKAVARPROXY.exe)
Port 7000 has to set open from the SmartPad:
Start-up -> Network configuration -> NAT -> Add port -> Port number 7000 and Permitted protocols: tcp/udp 

Video showing a KUKA KR6 R900 sixx being controlled using JOpenShowVar as the communication interface
http://youtu.be/T5owhNRG9VA

Example code
===========

```java
public class Example {

	private static String robotIP = "192.168.2.2";
	private static int port = 7000;

	public static void main(String[] args) throws IOException {
		CrosscomClient client = new CrossComClient(robotIP, port);  //establish connection
		
		Callback readRequest = client.sendRequest(new Request(0, "$OV_JOG")); //read request
		System.out.println(readRequest);
		
		Callback writeRequest = client.sendRequest(new Request(1, "$OV_JOG", "100")); //write request
		System.out.println(writeRequest);
	}

}
```

Acknowledgements
==============
A big thanks to Massimiliano Fago, which is the original author of KUKAVARPROXY and the C++ project OpenShowVar (http://sourceforge.net/projects/openshowvar/)
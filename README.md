JOpenShowVar
============

JOpenShowVar is a Java open-source cross-platform communication interface to Kuka robots that allows for reading and writing variables and data structures of the controlled manipulators. This interface, which is compatible with all Kuka robots that use KR C4 and previous versions, runs as a client on a remote computer connected with the Kuka controller via TCP/IP. JOpenShowVar opens up to a variety of possible applications making it possible to use different input devices and to develop alternative control methods.

To show the potential of the proposed interface, two case studies are presented. In the first one, JOpenShowVar is used to control a Kuka KR 6 R900 SIXX (KR AGILUS) robot from an Android mobile device. In the second case, the same manipulator is controlled by using a Leap Motion Controller that supports hand and finger motions as input without requiring contact or touching. Related simulations are carried out to validate efficiency and flexibility of the proposed communication interface.


http://youtu.be/T5owhNRG9VA

Example code:
'''java
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
'''
package pe.com.foxdev.camel;

import org.apache.camel.builder.RouteBuilder;

public class FtpClientRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("file:target/upload?moveFailed=../error").log("Uploading file ${file:name}").to("{{ftp.server}}").log("Uploaded file ${file:name} complete.");
	}

}

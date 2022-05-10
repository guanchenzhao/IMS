package com.gc.ims.ws;


import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class WsController {
    private static final String NAMESPACE_URI = "http://www.tcss559.com/xml/museum";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "MuseumInfoRequest")
    @ResponsePayload
    public MuseumInfoResponse getUser(@RequestPayload MuseumInfoRequest request) {
        MuseumInfoResponse response = new MuseumInfoResponse();
        MuseumInfo info = new MuseumInfo();
        info.setAddress("1933 Dock st., Tacoma, WA");
        info.setEmail("tcss559@uw.edu");
        info.setName("Group3 Museum");
        info.setPhone("1234567890");
        info.setOpenTime("07:00 - 21:00");
        response.setMuseumInfo(info);
        return response;
    }
}

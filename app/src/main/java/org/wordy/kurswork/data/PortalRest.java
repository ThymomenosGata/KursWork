package org.wordy.kurswork.data;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PortalRest {

    private final static String BASE_URL = "http://165.22.129.138";
    private static final MediaType JSON = MediaType.parse("application/json");
    private static OkHttpClient client = new OkHttpClient();

    public PortalRest() {
    }

    public static PortalRest getPortal() {
        return new PortalRest();
    }

    private static Request.Builder getRequestBuilder(String... segments) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();

        for (String segment : segments) {
            urlBuilder.addPathSegment(segment);
        }

        return new Request.Builder()
                .url(urlBuilder.build());
    }

    private static Response makeRequest(Request request) throws IOException {
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        return response;
    }

    public Response post(String json, String... segments) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        return makeRequest(getRequestBuilder(segments).post(body).build());
    }

    public Response get(String... segments) throws IOException {
        return makeRequest(getRequestBuilder(segments).get().build());
    }

    public Response update(String json, String... segments) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        return makeRequest(getRequestBuilder(segments).put(body).build());
    }

    public Response delete(String... segments) throws IOException {
        return makeRequest(getRequestBuilder(segments).delete().build());
    }
}

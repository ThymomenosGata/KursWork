package org.wordy.kurswork.data;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PortalRest {

    private final static String BASE_URL = "http://gdz-geo.myjino.ru/query.php";
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

    public Response post(String json) throws IOException {
        RequestBody body = new FormBody.Builder().add("sql", json).build();
        return makeRequest(getRequestBuilder().post(body).build());
    }

}

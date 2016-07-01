package com.example.alainbansais.creators;

import com.example.alainbansais.model.CreatorMarvel;
import com.example.alainbansais.model.ResultWrapper;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitListCreatorRepository implements ListCreatorRepository {
    private static final String API_URL = "http://gateway.marvel.com/v1/public/";
    private static final String API_KEY = "e3abbc2f4d802fb69bc83f77a5463870";
    private static final String HASH = "24807b3e495349dd682c2171df9bd96c";
    private final Retrofit retrofit;

    interface ListCreatorService {

        @GET("creators")
        Call<ResultWrapper<CreatorMarvel>> getListCreator(
                @Query("limit") int limit,
                @Query("ts") int timeStamp,
                @Query("apikey") String apiKey,
                @Query("hash") String hash
        );
    }

    public RetrofitListCreatorRepository() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    @Override
    public List<CreatorMarvel> get() throws RepositoryCreatorException {
        List<CreatorMarvel> marvelList = null;
        try {
            marvelList = retrofit.create(ListCreatorService.class)
                                 .getListCreator(60, 1, API_KEY, HASH)
                                 .execute()
                                 .body()
                                 .getResultContainer()
                                 .getList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (marvelList == null) {
            throw new RepositoryCreatorException();
        }

        return marvelList;
    }
}

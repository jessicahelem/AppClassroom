package com.example.rodrigosouza.presencebarcode.util;

import com.example.rodrigosouza.presencebarcode.interfaces.DeclaracaoAusenciaService;
import com.example.rodrigosouza.presencebarcode.interfaces.HorarioService;
import com.example.rodrigosouza.presencebarcode.interfaces.TurmaService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ConnectionRetrofit {
    private final Retrofit retrofit;
    public ConnectionRetrofit(){
        this.retrofit =  new Retrofit.Builder().baseUrl("http://172.16.206.115:8000/api/")
        //this.retrofit =  new Retrofit.Builder().baseUrl("http://172.16.216.203/api/")
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }

    public DeclaracaoAusenciaService service(){
        return this.retrofit.create(DeclaracaoAusenciaService.class);
    }

    public HorarioService horarioService(){
        return this.retrofit.create(HorarioService.class);
    }


    public TurmaService turmaService(){
        return  this.retrofit.create(TurmaService.class);
    }
}


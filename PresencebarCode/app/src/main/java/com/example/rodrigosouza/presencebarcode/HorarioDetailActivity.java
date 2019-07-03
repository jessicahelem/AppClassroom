package com.example.rodrigosouza.presencebarcode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rodrigosouza.presencebarcode.model.DeclaracaoAusencia;
import com.example.rodrigosouza.presencebarcode.model.Turma;
import com.example.rodrigosouza.presencebarcode.util.ConnectionRetrofit;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HorarioDetailActivity extends AppCompatActivity {
    private Button disp;
    private Button cancel, solicitar;
    private TextView hoIni, hoFim, tur,curso;
    private TextView prof, discp, cargaHo;
    private final String professorLogado = "1";
    private String date, horaInicio;
    private Turma turmaRes;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_detail);
        context = this.context;
        bindView();
    }
    public void disponiblizar(View v){
        DeclaracaoAusencia ausencia = new DeclaracaoAusencia();
        ausencia.setData_falta(date);
        ausencia.setProfessor("58912698");
        ausencia.setTurma(turmaRes.getEspecificacao_disciplina());
        ausencia.setHorario(horaInicio);
        SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd");
        Date hoje = new Date();
        String Sdata = fDate.format(hoje);
        ausencia.setData_declaracao(Sdata);
        Call<DeclaracaoAusencia> ausenciaCall = new ConnectionRetrofit().service().declararAusencia(ausencia);
        ausenciaCall.enqueue(new Callback<DeclaracaoAusencia>() {
            @Override
            public void onResponse(Call<DeclaracaoAusencia> call, Response<DeclaracaoAusencia> response) {
                if(response.body() != null){
                    Toast.makeText(context, "O horário foi disponibilizado para outros professores!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeclaracaoAusencia> call, Throwable t) {

            }
        });

        disp.setVisibility(View.GONE);
        cancel.setVisibility(View.VISIBLE);

    }
    public void SolicitarHorario(View v){
        solicitar.setVisibility(View.GONE);
        cancel.setVisibility(View.VISIBLE);
    }
    public void cancelarDispon(View v){
        Toast.makeText(this, "A ação foi cancelada!", Toast.LENGTH_SHORT).show();
        if(professorLogado == "1"){
            disp.setVisibility(View.VISIBLE);
        }else{
            solicitar.setVisibility(View.VISIBLE);
        }
        cancel.setVisibility(View.GONE);
    }

    public void getTurma(String idTurma){
        Call<Turma> turmaCall = new ConnectionRetrofit().horarioService().getTurma(idTurma);
        turmaCall.enqueue(new Callback<Turma>() {
            @Override
            public void onResponse(Call<Turma> call, Response<Turma> response) {
                turmaRes = response.body();
                showData(turmaRes);
            }

            @Override
            public void onFailure(Call<Turma> call, Throwable t) {

            }
        });
    }

    public void showData(Turma turma){
        tur.setText(turma.getEspecificacao_disciplina());
        prof.setText(turma.getMinistrante());
        discp.setText(turma.getDisciplina());
        curso.setText(turma.getCurso());
        cargaHo.setText(turma.getCarga_horaria());
    }

    public void bindView(){
        Intent intent = getIntent();
        String id =  intent.getStringExtra("idHorario");
        String turma =  intent.getStringExtra("turma");
        horaInicio =  intent.getStringExtra("horaInicio");
        String horaFim =  intent.getStringExtra("horaFim");
        date =  intent.getStringExtra("date");

        disp = (Button) findViewById(R.id.bt_disponibilizar);
        cancel = (Button)findViewById(R.id.bt_cancelar);
        solicitar = findViewById(R.id.bt_detail_solicitar);
        if(!professorLogado.equals("1")){
            solicitar.setVisibility(View.VISIBLE);
            disp.setVisibility(View.GONE);
        }
        prof = (TextView)findViewById(R.id.txt_professor_nome);
        hoIni = findViewById(R.id.txt_horario_hora_inicio);
        hoIni.setText(horaInicio);
        hoFim = findViewById(R.id.txt_horario_hora_fim);
        hoFim.setText(horaFim);
        discp = findViewById(R.id.txt_disciplina_nome);
        tur = findViewById(R.id.txt_turma);
        curso = findViewById(R.id.txt_title_detail);
        cargaHo = findViewById(R.id.txt_carga_horaria);
        getTurma(turma);
    }
}

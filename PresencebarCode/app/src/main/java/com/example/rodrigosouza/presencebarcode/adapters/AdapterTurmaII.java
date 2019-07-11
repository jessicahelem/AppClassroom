package com.example.rodrigosouza.presencebarcode.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rodrigosouza.presencebarcode.R;
import com.example.rodrigosouza.presencebarcode.model.Turma;

import java.util.List;

public class AdapterTurmaII extends BaseAdapter {
    private final List<Turma> turmas;
    private Context context;

    public AdapterTurmaII(List<Turma> turmas, Context c){
        this.turmas = turmas;
        this.context =c;

    }
    @Override
    public int getCount() {
        return turmas.size();
    }

    @Override
    public Object getItem(int position) {
        return turmas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        itemView = LayoutInflater.from(context).inflate(R.layout.item_lista_turma, parent, false);
        final Turma turma = turmas.get(position);
        //TextView text_especificacao_disciplina_id = itemView.findViewById(R.id.textEspDisciplina);
        //TextView text_carga_horaria_id = itemView.findViewById(R.id.detalhesCargaHoraria );
        TextView input_especificacao_id = itemView.findViewById(R.id.inputEspDisciplina);
        //TextView textDisciplina_id = itemView.findViewById(R.id.textDisciplina);
        TextView inputDisciplina_id = itemView.findViewById(R.id.inputDisciplina);
        TextView inputCargaHoraria_id = itemView.findViewById(R.id.inputCargaHoraria);
        //text_especificacao_disciplina_id.setText(turma.getEspecificacao_disciplina());
        //text_carga_horaria_id.setText(turma.getCarga_horaria());
        input_especificacao_id.setText(turma.getEspecificacao_disciplina());
        inputCargaHoraria_id.setText(turma.getCarga_horaria());
        inputDisciplina_id.setText(turma.getDisciplina());
        return null;
    }
}

package zxy.android.calcularnota;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

    public class MainActivity extends AppCompatActivity {

        EditText nota_1, nota_2, nota_3, nota_4, pond_1, pond_2, pond_3, pond_4, asistencia;
        TextView promedio, situacion;

        CheckBox usar_concepto;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            nota_1 = findViewById(R.id.nota_1);
            nota_2 = findViewById(R.id.nota_2);
            nota_3 = findViewById(R.id.nota_3);
            nota_4 = findViewById(R.id.nota_4);
            pond_1 = findViewById(R.id.ponde_1);
            pond_2 = findViewById(R.id.ponde_2);
            pond_3 = findViewById(R.id.ponde_3);
            pond_4 = findViewById(R.id.ponde_4);
            promedio = findViewById(R.id.nota);
            asistencia = findViewById(R.id.asistencia);
            situacion = findViewById(R.id.situacion);
            usar_concepto = findViewById(R.id.usar_concepto);
        }

        public void calcular(View view) {
            String nota1 = nota_1.getText().toString();
            String nota2 = nota_2.getText().toString();
            String nota3 = nota_3.getText().toString();
            String nota4 = nota_4.getText().toString();

            String pond1 = pond_1.getText().toString();
            String pond2 = pond_2.getText().toString();
            String pond3 = pond_3.getText().toString();
            String pond4 = pond_4.getText().toString();

            String asist = asistencia.getText().toString();
            String[] global = {pond1, pond2, pond3, pond4,nota1, nota2, nota3, nota4, asist};


            int contador = 0;
            for (int i = 0; i < global.length; i++) {
                if (validar_vacio(global[i])) {
                    contador++;
                }
            }

            if (contador > 0){
                promedio.setText("Debe llenar todos los campos");
                situacion.setText("");
            } else {

                double n1 = Double.parseDouble(nota1);
                double n2 = Double.parseDouble(nota2);
                double n3 = Double.parseDouble(nota3);
                double n4 = Double.parseDouble(nota4);

                double p1 = Double.parseDouble(pond1);
                double p2 = Double.parseDouble(pond2);
                double p3 = Double.parseDouble(pond3);
                double p4 = Double.parseDouble(pond4);
                int assist = Integer.parseInt(asistencia.getText().toString());
                double[] notas = {n1, n2, n3, n4};
                int contador2 = 0;

                for (int i = 0; i < notas.length; i++) {
                    if (!validar_nota(notas[i])) {
                        contador2++;
                    }
                }
                if (contador2 > 0){
                    promedio.setText("Las notas deben estar entre 1 y 7");
                    situacion.setText("");
                } else if ((p1 + p2 + p3 + p4) != 100) {
                    promedio.setText("La suma de las ponderaciones debe ser 100%");
                    situacion.setText("");
                } else if (p4 > 20) {
                    promedio.setText("Ponderacion 4 no debe superar el 20%");
                    situacion.setText("");
                } else if (assist < 70) {
                    promedio.setText("Asistencia debe ser mayor al 70%");
                    situacion.setText("Reprobado");
                } else {
                    double total = (n1 * p1 + n2 * p2 + n3 * p3 + n4 * p4) / (p1 + p2 + p3 + p4);
                    if (!usar_concepto.isChecked()) {
                        promedio.setText(total + "");

                        if (total >= 4.0) {
                            situacion.setText("Aprobado");
                        } else if (total < 4.0) {
                            situacion.setText("Reprobado");
                        } else
                            situacion.setText("Error");
                    } else {
                        if (total < 4.0) {
                            promedio.setText("Insuficiente");
                            situacion.setText("Reprobado");
                        } else if (total >= 4.0 && total < 5.0) {
                            promedio.setText("Suficiente");
                            situacion.setText("Aprobado");
                        } else if (total >= 5.0 && total < 6.0) {
                            promedio.setText("Bueno");
                            situacion.setText("Aprobado");
                        } else if (total >= 6.0 && total < 7.0) {
                            promedio.setText("Excelente");
                            situacion.setText("Aprobado");
                        }
                    }

                }
            }

        }
        public boolean validar_nota(double nota){
            if (nota < 1 || nota > 7){
                return false;
            }
            else
                return true;
        }

        public boolean validar_vacio(String valite){
        return (valite.matches(""));
        }

    }
package com.example.apputnsql;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // Definir los campos de entrada
    private EditText etCedula, etNombre, etApellido, etPais, etFechaNacimiento;
    private Button btnCrear, btnLeer, btnActualizar, btnEliminar;

    // Base de datos simulada usando HashMap
    private HashMap<String, Author> authorsDB = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular los campos y botones con sus IDs
        etCedula = findViewById(R.id.etCedula);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etPais = findViewById(R.id.etPais);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);

        btnCrear = findViewById(R.id.btnCrear);
        btnLeer = findViewById(R.id.btnLeer);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);

        // Función Crear
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedula = etCedula.getText().toString();
                if (!cedula.isEmpty() && !authorsDB.containsKey(cedula)) {
                    Author author = new Author(
                            cedula,
                            etNombre.getText().toString(),
                            etApellido.getText().toString(),
                            etPais.getText().toString(),
                            etFechaNacimiento.getText().toString()
                    );
                    authorsDB.put(cedula, author);
                    Toast.makeText(MainActivity.this, "Autor creado con éxito", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                } else if (cedula.isEmpty()) {
                    Toast.makeText(MainActivity.this, "El campo Cédula está vacío", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "El autor ya existe", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Función Leer
        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedula = etCedula.getText().toString();
                if (authorsDB.containsKey(cedula)) {
                    Author author = authorsDB.get(cedula);
                    etNombre.setText(author.getNombre());
                    etApellido.setText(author.getApellido());
                    etPais.setText(author.getPais());
                    etFechaNacimiento.setText(author.getFechaNacimiento());
                    Toast.makeText(MainActivity.this, "Datos del autor cargados con éxito", Toast.LENGTH_SHORT).show();
                } else if (cedula.isEmpty()) {
                    Toast.makeText(MainActivity.this, "El campo Cédula está vacío", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Autor no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Función Actualizar
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedula = etCedula.getText().toString();
                if (authorsDB.containsKey(cedula)) {
                    Author author = new Author(
                            cedula,
                            etNombre.getText().toString(),
                            etApellido.getText().toString(),
                            etPais.getText().toString(),
                            etFechaNacimiento.getText().toString()
                    );
                    authorsDB.put(cedula, author);
                    Toast.makeText(MainActivity.this, "Autor actualizado con éxito", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                } else if (cedula.isEmpty()) {
                    Toast.makeText(MainActivity.this, "El campo Cédula está vacío", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Autor no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Función Eliminar
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedula = etCedula.getText().toString();
                if (authorsDB.containsKey(cedula)) {
                    authorsDB.remove(cedula);
                    limpiarCampos();
                    Toast.makeText(MainActivity.this, "Autor eliminado con éxito", Toast.LENGTH_SHORT).show();
                } else if (cedula.isEmpty()) {
                    Toast.makeText(MainActivity.this, "El campo Cédula está vacío", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Autor no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para limpiar los campos de entrada después de realizar una operación
    private void limpiarCampos() {
        etCedula.setText("");
        etNombre.setText("");
        etApellido.setText("");
        etPais.setText("");
        etFechaNacimiento.setText("");
    }

    // Clase para representar un Autor
    public class Author {
        private String cedula;
        private String nombre;
        private String apellido;
        private String pais;
        private String fechaNacimiento;

        public Author(String cedula, String nombre, String apellido, String pais, String fechaNacimiento) {
            this.cedula = cedula;
            this.nombre = nombre;
            this.apellido = apellido;
            this.pais = pais;
            this.fechaNacimiento = fechaNacimiento;
        }

        public String getCedula() {
            return cedula;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public String getPais() {
            return pais;
        }

        public String getFechaNacimiento() {
            return fechaNacimiento;
        }
    }
}

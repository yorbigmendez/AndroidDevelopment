package cr.ac.itcr.fragmentsexample;//package net.sgoliver.android.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DetalleActivity extends AppCompatActivity {
	
	public static int CORREO_SELECCIONADO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle);
		
		FragmentDetalle detalle = 
				(FragmentDetalle)getSupportFragmentManager()
					.findFragmentById(R.id.FrgDetalle);
		detalle.mostrarDetalle(CORREO_SELECCIONADO);
	}
}

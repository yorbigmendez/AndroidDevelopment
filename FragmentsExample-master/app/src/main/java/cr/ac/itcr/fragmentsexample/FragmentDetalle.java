package cr.ac.itcr.fragmentsexample;

import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentDetalle extends Fragment {
	RadioGroup rgLike;
	RadioButton rbSelected;
	TextView tvLike;
    Button btnSubmit;
	ImageView imgEmail;

	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_detalle, container, false);
	}
	
	public void mostrarDetalle(int indiceCorreo) {
		TextView txtDetalle = 
				(TextView)getView().findViewById(R.id.TxtDetalle);
		txtDetalle.setText(FragmentListado.datos[indiceCorreo].getTexto());
        if(FragmentListado.datos[indiceCorreo].getMeGusta() != ""){
            tvLike.setVisibility(View.VISIBLE);
            tvLike.setText("Tu respuesta si te gusta es: " + FragmentListado.datos[indiceCorreo].getMeGusta());
        }else
            tvLike.setVisibility(View.INVISIBLE);
		switch(FragmentListado.datos[indiceCorreo].getImg()){
			case 1:
				imgEmail.setImageResource(R.drawable.email1);
				break;
			case 2:
				imgEmail.setImageResource(R.drawable.email2);
				break;
		}
	}



	@Override
	public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
		imgEmail = (ImageView)getView().findViewById(R.id.imageEmail);
        btnSubmit = (Button)getView().findViewById(R.id.btnSubmit);
		rgLike = (RadioGroup)getView().findViewById(R.id.radioGroupLike);
		tvLike = (TextView)getView().findViewById(R.id.txtLiked);
		tvLike.setVisibility(View.INVISIBLE);
		btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbSelected = (RadioButton) getView().findViewById(rgLike.getCheckedRadioButtonId());
                tvLike.setVisibility(View.VISIBLE);
				FragmentListado.datos[DetalleActivity.CORREO_SELECCIONADO].setMeGusta(rbSelected.getText().toString());
                tvLike.setText("Tu respuesta si te gusta es: " + FragmentListado.datos[DetalleActivity.CORREO_SELECCIONADO].getMeGusta());
            }
        });

	}

}

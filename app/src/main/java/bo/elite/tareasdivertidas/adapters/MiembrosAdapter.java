package bo.elite.tareasdivertidas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bo.elite.tareasdivertidas.models.Miembro;
import bo.elite.tareasdivertidas.R;

public class MiembrosAdapter extends BaseAdapter {
    private Context mContext;
    private List<Miembro> items;

    public MiembrosAdapter(Context context, List<Miembro> items){
        this.mContext = context;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Miembro getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View vista, ViewGroup parent) {
        ViewHolder viewHolder;
        if (vista == null) { //No se puede reciclar
            viewHolder = new ViewHolder();

            //Inflater nos permite usar un layout dentro de un componente
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflater.inflate(R.layout.layout_miembro, null); //Cual layout y principal o no.

            viewHolder.icono = vista.findViewById(R.id.icono);
            viewHolder.nombre = vista.findViewById(R.id.nombreMiembro);
            viewHolder.edad = vista.findViewById(R.id.edadMiembro);
            viewHolder.puntaje = vista.findViewById(R.id.puntaje);
            //viewHolder.email = vista.findViewById(R.id.emailMiembro);
            vista.setTag(viewHolder); //Guardar para reciclar
        } else {
            viewHolder = (ViewHolder) vista.getTag(); //Obtener el dato reciclado
        }

        Miembro miembro = items.get(position);
        viewHolder.nombre.setText(miembro.getNombre());
        viewHolder.edad.setText(toString().valueOf(miembro.getEdad()));
        //Drawable image = mContext.getResources().getDrawable(miembro.getIcono());
        //viewHolder.icono.setImageDrawable(getResources().getDrawable(miembro.getIcono()));
        viewHolder.icono.setImageResource(miembro.getIcono());
        viewHolder.puntaje.setText(miembro.getPuntaje() + " punto(s)");
        //viewHolder.email.setText(miembro.getCorreoElectronico());
        return vista;
    }

    static class ViewHolder {
        ImageView icono;
        TextView nombre;
        TextView edad;
        //TextView email;
        TextView puntaje;
    }
}

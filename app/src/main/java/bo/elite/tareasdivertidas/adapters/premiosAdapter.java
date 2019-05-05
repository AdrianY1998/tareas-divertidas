package bo.elite.tareasdivertidas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import bo.elite.tareasdivertidas.Premio;
import bo.elite.tareasdivertidas.R;


public class premiosAdapter extends BaseAdapter {
    private Context mContext;
    private List<Premio> premios;

    public premiosAdapter(Context mContext, List<Premio> premios) {
        this.mContext = mContext;
        this.premios = premios;

    }

    @Override
    public int getCount() {
        return premios.size();
    }

    @Override
    public Object getItem(int position) {
        return premios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return premios.get(position).getId();
    }

    @Override
    public View getView(int position, View vista, ViewGroup parent) {
        ViewHolder viewHolder;
        if (vista == null) { //No se puede reciclar
            viewHolder = new ViewHolder();

            //Inflater nos permite usar un layout dentro de un componente
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflater.inflate(R.layout.premio, null); //Cual layout y principal o no.

            viewHolder.image = vista.findViewById(R.id.image);
            viewHolder.nombre = vista.findViewById(R.id.nombrePremio);
            vista.setTag(viewHolder); //Guardar para reciclar
        } else {
            viewHolder = (ViewHolder) vista.getTag(); //Obtener el dato reciclado
        }

        Premio premio = premios.get(position);
        viewHolder.nombre.setText(premio.getNombrePremio());
        viewHolder.image.setImageResource(premio.getImage());
        return vista;
    }
    static class ViewHolder {
        ImageView image;
        TextView nombre;
    }
}

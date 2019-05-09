package bo.elite.tareasdivertidas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bo.elite.tareasdivertidas.Miembro;
import bo.elite.tareasdivertidas.MiembrosAEvaluar;
import bo.elite.tareasdivertidas.R;

public class EvaluacionAdapter extends BaseAdapter {

    private Context mContext;
    private List<Miembro> items;

    public EvaluacionAdapter(Context mContext, List<Miembro> miembros) {
        this.mContext=mContext;
        this.items = miembros;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View vista, ViewGroup parent) {
        ViewHolder viewHolder;
        if (vista==null){
            viewHolder= new ViewHolder();

            LayoutInflater inflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflater.inflate(R.layout.layout_evaluacion, null);

            viewHolder.icono = vista.findViewById(R.id.icono);
            viewHolder.nombre = vista.findViewById(R.id.nombreMiembro);
            vista.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) vista.getTag(); //Obtener el dato reciclado
        }
        Miembro miembro = items.get(position);
        viewHolder.nombre.setText(miembro.getNombre());
        viewHolder.icono.setImageResource(miembro.getIcono());


        return vista;
    }

    static class ViewHolder{
        ImageView icono;
        TextView nombre;
    }
}

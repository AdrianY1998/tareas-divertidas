package bo.elite.tareasdivertidas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import bo.elite.tareasdivertidas.R;
import bo.elite.tareasdivertidas.TareaAEvaluar;

public class TareasAEvaluarAdapter extends BaseAdapter {
    private Context mContext;
    private List<TareaAEvaluar> items;

    public TareasAEvaluarAdapter(Context mContext, List<TareaAEvaluar> items) {
        this.mContext=mContext;
        this.items = items;
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
        return items.get(position).getIDmiembro();
    }


    @Override
    public View getView(int position, View vista, ViewGroup parent) {
        ViewHolder viewHolder;
        if (vista==null){
            viewHolder= new ViewHolder();

            LayoutInflater inflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflater.inflate(R.layout.layout_tarea_evaluar, null);

            viewHolder.tarea = vista.findViewById(R.id.tarea);
            vista.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) vista.getTag(); //Obtener el dato reciclado
        }
        TareaAEvaluar miembro = items.get(position);

        viewHolder.tarea.setImageResource(miembro.getTarea());


        return vista;
    }

    static class ViewHolder{
        ImageView tarea;
    }
}

package bo.elite.tareasdivertidas.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bo.elite.tareasdivertidas.Callback.TareaCallback;
import bo.elite.tareasdivertidas.R;
import bo.elite.tareasdivertidas.models.Tarea;

public class tareaRecyclerViewAdapter2 extends RecyclerView.Adapter<TareaViewHolder> {
    private List<Tarea> tareaList;
    private LayoutInflater inflater;
    private TareaCallback tareaCallback;
    //d

    public tareaRecyclerViewAdapter2(Context context, List<Tarea> tareaList) {
        this.tareaList = tareaList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = this.inflater.inflate(R.layout.tarea_asiganada, null);
        return new TareaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder TareaViewHolder, int position) {
        final Tarea tarea = this.tareaList.get(position);
        TareaViewHolder.imageView.setImageResource(tarea.getImageTarea());
        TareaViewHolder.textViewName.setText(tarea.getNameTarea());
        TareaViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tareaCallback != null) {
                    tareaCallback.onTareaClick(tarea);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.tareaList.size();
    }

    public void setTareaCallback(TareaCallback tareaCallback) {
        this.tareaCallback = tareaCallback;
    }
}

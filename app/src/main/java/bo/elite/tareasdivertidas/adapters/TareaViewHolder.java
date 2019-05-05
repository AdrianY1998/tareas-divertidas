package bo.elite.tareasdivertidas.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import bo.elite.tareasdivertidas.R;

public class TareaViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textViewName;
    public TextView textViewPto;
    //d

    public TareaViewHolder(@NonNull View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.imagenTarea);
        this.textViewName = itemView.findViewById(R.id.nombreTarea);
        this.textViewPto = itemView.findViewById(R.id.puntajeTarea);
}
}


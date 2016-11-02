package com.example.emiliano.prueba.ViewHolders;

/**
 * Created by leo on 02/11/16.
 */

    import android.support.v7.widget.RecyclerView;
    import android.view.View;
    import android.widget.ImageView;
    import android.widget.LinearLayout;
    import android.widget.TextView;


    import com.example.emiliano.prueba.R;

    import java.text.DecimalFormat;

/**
 * Created by leo on 30/09/16.
 */
public class ListJugadoresVH extends RecyclerView.ViewHolder  {
    public ImageView icon;
    public TextView nombre;
    public TextView equipo;
    public TextView precio;
    public LinearLayout itemJug;

    public ListJugadoresVH(View itemView) {
        super(itemView);
        icon = (ImageView) itemView.findViewById(R.id.icon);
        nombre = (TextView) itemView.findViewById(R.id.nombreJugador);
        equipo = (TextView) itemView.findViewById(R.id.equipoJugador);
        precio = (TextView) itemView.findViewById(R.id.precio);
        itemJug = (LinearLayout) itemView.findViewById(R.id.itemJug);
    }
}
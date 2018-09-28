package com.example.mj975.woder_woman.adpater.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.mj975.woder_woman.R;
import com.example.mj975.woder_woman.data.Toilet;

public class DangerZoneViewHolder extends AbstractViewHolder {
    private TextView titleText;
    private TextView infoText;

    public DangerZoneViewHolder(@NonNull View itemView) {
        super(itemView);
        titleText = itemView.findViewById(R.id.toilet_title);
        infoText = itemView.findViewById(R.id.toilet_info);
    }

    @Override
    public void onBindView(@NonNull Object item, int position) {
        Toilet t = (Toilet) item;
        titleText.setText(t.getContent());
        infoText.setText(t.getOpenTime());
    }
}

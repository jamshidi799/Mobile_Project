package com.burhanrashid52.photoeditor.tools;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.burhanrashid52.photoeditor.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://github.com/burhanrashid52">Burhanuddin Rashid</a>
 * @version 0.1.2
 * @since 5/23/2018
 */
public class EditingToolsAdapter extends RecyclerView.Adapter<EditingToolsAdapter.ViewHolder> {

    private List<ToolModel> mToolList = new ArrayList<>();
    private OnItemSelected mOnItemSelected;

    public EditingToolsAdapter(OnItemSelected onItemSelected, ActivityType activityType) {
        mOnItemSelected = onItemSelected;
        switch (activityType) {
            case EDITING:
                setEditingActivityTools();
                break;
            case COLLAGE:
                setCollageActivityTools();
                break;
        }
    }

    private void setCollageActivityTools() {
        mToolList.add(new ToolModel("Camera", R.drawable.ic_camera, ToolType.CAMERA));
        mToolList.add(new ToolModel("Gallery", R.drawable.ic_gallery, ToolType.GALLERY));
    }
    private void setEditingActivityTools() {
        mToolList.add(new ToolModel("Crop", R.drawable.ic_baseline_crop_24, ToolType.CROP));
        mToolList.add(new ToolModel("Rotate Right", R.drawable.ic_baseline_rotate_right_24, ToolType.ROTATE_RIGHT));
        mToolList.add(new ToolModel("Rotate Left", R.drawable.ic_baseline_rotate_left_24, ToolType.ROTATE_LEFT));
        mToolList.add(new ToolModel("Brush", R.drawable.ic_brush, ToolType.BRUSH));
        mToolList.add(new ToolModel("Eraser", R.drawable.ic_eraser, ToolType.ERASER));
        mToolList.add(new ToolModel("Text", R.drawable.ic_text, ToolType.TEXT));
        mToolList.add(new ToolModel("Filter", R.drawable.ic_photo_filter, ToolType.FILTER));
        mToolList.add(new ToolModel("Emoji", R.drawable.ic_insert_emoticon, ToolType.EMOJI));
        mToolList.add(new ToolModel("Sticker", R.drawable.ic_sticker, ToolType.STICKER));
        mToolList.add(new ToolModel("Adjust", R.drawable.ic_baseline_adjust_24, ToolType.ADJUSTMENT));
        mToolList.add(new ToolModel("Collage", R.drawable.ic_baseline_view_column_24, ToolType.COLLAGE));
//        mToolList.add(new ToolModel("Frame", R.drawable.ic_frame, ToolType.FRAME));
    }

    public interface OnItemSelected {
        void onToolSelected(ToolType toolType);
    }

    class ToolModel {
        private String mToolName;
        private int mToolIcon;
        private ToolType mToolType;

        ToolModel(String toolName, int toolIcon, ToolType toolType) {
            mToolName = toolName;
            mToolIcon = toolIcon;
            mToolType = toolType;
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_editing_tools, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToolModel item = mToolList.get(position);
        holder.txtTool.setText(item.mToolName);
        holder.imgToolIcon.setImageResource(item.mToolIcon);
    }

    @Override
    public int getItemCount() {
        return mToolList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgToolIcon;
        TextView txtTool;

        ViewHolder(View itemView) {
            super(itemView);
            imgToolIcon = itemView.findViewById(R.id.imgToolIcon);
            txtTool = itemView.findViewById(R.id.txtTool);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemSelected.onToolSelected(mToolList.get(getLayoutPosition()).mToolType);
                }
            });
        }
    }
}

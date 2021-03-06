/*
 * Copyright 2014 Soichiro Kashima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appmanager.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appmanager.android.R;
import com.appmanager.android.entity.FileEntry;

import java.util.List;

public class FileEntryAdapter extends ArrayAdapter<FileEntry> {

    public interface OnClickListener {
        void onClick(final View view, final FileEntry fileEntry);
    }

    private OnClickListener mOnClickListener;

    public FileEntryAdapter(final Context context, List<FileEntry> objects) {
        super(context, R.layout.row_file_entry, R.id.name, objects);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        FileEntry entity = getItem(position);
        if (entity == null) {
            entity = new FileEntry();
        }
        ((TextView) view.findViewById(R.id.name)).setText(entity.name);
        ((TextView) view.findViewById(R.id.url)).setText(entity.url);
        view.setTag(entity);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (mOnClickListener != null) {
                    Object tag = view.getTag();
                    if (tag instanceof FileEntry) {
                        FileEntry entry = (FileEntry) tag;
                        mOnClickListener.onClick(view, entry);
                    }
                }
            }
        });
        return view;
    }

    public void setOnClickListener(final OnClickListener listener) {
        mOnClickListener = listener;
    }
}

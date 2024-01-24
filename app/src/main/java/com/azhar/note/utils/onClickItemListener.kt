package com.azhar.note.utils

import com.azhar.note.model.ModelNote



interface onClickItemListener {
    fun onClick(modelNote: ModelNote, position: Int)
}
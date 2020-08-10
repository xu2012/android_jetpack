package com.xu.test.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.xu.test.R
import kotlinx.android.synthetic.main.activity_custom.*
import java.util.ArrayList

class CustomActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var dialog:BottomSheetDialog
    private lateinit var mDialogBehavior:BottomSheetBehavior<*>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom)
        btnDialog.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnDialog -> {
                showBottomSheetDialog()
                dialog.show()
            }
            else -> {
            }
        }
    }

    private fun showBottomSheetDialog() {
        val view = View.inflate(this,R.layout.dialog_bottomsheet,null)
        val recyclerview=view.findViewById<RecyclerView>(R.id.dialog_recycleView)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val list = ArrayList<String>()
        for(index in 0..10){
            list.add("第${index}条")
        }
        recyclerview.adapter = DialogAdapter(R.layout.item_dialog,list)

        dialog = BottomSheetDialog(this,R.style.DialogTheme)
        dialog.setContentView(view)
        mDialogBehavior = BottomSheetBehavior.from(view.parent as View)
        mDialogBehavior.peekHeight = 500
    }
}
package com.example.currencyconverter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_drop_down_menu.view.*
import android.graphics.BitmapFactory
import androidx.core.view.setPadding

import java.net.URL




class CustomSpinnerAdapter(context: Context, var listItemsTxt: Array<CurrencyISO>) : BaseAdapter()  {
    val mInflater: LayoutInflater = LayoutInflater.from(context)

    //renvoie la vue du dropdown du spinner
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder

        if (convertView == null) {
            view = mInflater.inflate(R.layout.view_drop_down_menu, parent, false)
            vh = ItemRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemRowHolder
        }

        // setting adapter item height programatically.

        val params = view.layoutParams
        params.height = 60
        view.layoutParams = params

        vh.label.setText(listItemsTxt.get(position).currencyName)
        //telechargement de l'image
        Picasso.get().load(listItemsTxt.get(position).imageUrl).into(vh.image)

        return view
    }


    override fun getItem(position: Int): Any? {

        return null

    }

    override fun getItemId(position: Int): Long {

        return 0

    }


    //retourne la taille du taleau listItemsTxt
    override fun getCount(): Int {
        return listItemsTxt.size
    }

    //insere dans le drp down du spinner image et texte correspondant
    private class ItemRowHolder(row: View?) {

        val label: TextView
        val image : ImageView

        init {
            this.label = row?.findViewById(R.id.txtDropDownLabel) as TextView
            this.image = row.findViewById(R.id.imgDropDownMenuIcon) as ImageView
            row.setPadding(5)
        }
    }
}
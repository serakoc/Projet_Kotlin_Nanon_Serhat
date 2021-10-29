//declaration du package
package com.example.currencyconverter


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_main.*


//class mainActivity qui hérite d'AppCompatActivity...
class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //On place la vue sur le layout activity_main
        setContentView(R.layout.activity_main)

        //on insère tout les élements de l'enum dans les combos box / spinners
        currencySpinner1.adapter = CustomSpinnerAdapter(this, CurrencyISO.values())
        currencySpinner2.adapter = CustomSpinnerAdapter(this, CurrencyISO.values())

        //On place la valeur par défaut des deux spinners
        currencySpinner1.setSelection(43)
        currencySpinner2.setSelection(153, false)

        //Configuration du callback lorsqu'un élement est selectionné
        //l'evenement on itemSelected et onNothingSelected se trouve sur cette classe onc this
        currencySpinner1.onItemSelectedListener = this
        currencySpinner2.onItemSelectedListener = this


        //Valeur par défaut de l'input
        currencyTextInput1.setText(1.toString())

        //mises à jour du résultat lors du changement de texte de l'input
        currencyTextInput1.doAfterTextChanged {
            //Réalise le calcul de conversion de la devise
            applyConversion()
        }

        //évènement sur le bouton "inversé" -> on inverse la valeur des deux spinners
        invertBtn.setOnClickListener {
            val save = currencySpinner1.selectedItemPosition

            currencySpinner1.setSelection(currencySpinner2.selectedItemPosition, false)

            currencySpinner2.setSelection(save)
        }



    }


    // An item was selected. You can retrieve the selected item using
    // parent.getItemAtPosition(pos)
    // Evenement lors de la sélectron d'un élement d'un spinner
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) =  applyConversion()


    //on applique un champ vide si aucun élement n'est sélectionné
    override fun onNothingSelected(parent: AdapterView<*>) {
        convertionResultView1.text = ""
        convertionResultView2.text = ""
    }

    //Calcul de la conversion de devise ->

    private fun applyConversion() {
        // si l'input est rempli, on effectue le calcul des devises via convertTo()
        if (currencyTextInput1.text.toString() != "") {

            val selectedCurrency1 = CurrencyISO.values()[currencySpinner1.selectedItemPosition]
            val selectedCurrency2 = CurrencyISO.values()[currencySpinner2.selectedItemPosition]

            val aCurrency = Currency(selectedCurrency1)
            val convertionResult = aCurrency.convertTo(selectedCurrency2, currencyTextInput1.text.toDouble()).toBigDecimal().toPlainString()

            //mise en page
            val text1 = currencyTextInput1.text.toString() + " " + selectedCurrency1.currencyName + " = "
            val text2 = convertionResult.toDouble().rounded() + " " + selectedCurrency2.currencyName
            convertionResultView1.text = text1
            convertionResultView2.text = text2
        }
    }

    private fun Editable.toDouble() = toString().toDouble()

    private fun Double.rounded() : String = String.format("%.6f", this)
    }


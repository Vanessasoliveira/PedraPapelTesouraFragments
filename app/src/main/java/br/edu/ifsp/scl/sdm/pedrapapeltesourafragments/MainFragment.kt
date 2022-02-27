package br.edu.ifsp.scl.sdm.pedrapapeltesourafragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import br.edu.ifsp.scl.sdm.pedrapapeltesourafragments.databinding.FragmentMainBinding
import java.util.*
import kotlin.random.Random.Default.nextInt


class MainFragment : Fragment(), View.OnClickListener {

    private lateinit var fragmentMainBinding: FragmentMainBinding
    var jogadores = 0
    //private var valorComputador2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        Log.d("Create View", "Create the main fragment")
        fragmentMainBinding.papelBt.setOnClickListener(this)
        fragmentMainBinding.tesouraBt.setOnClickListener(this)
        fragmentMainBinding.pedraBt.setOnClickListener(this)

        setFragmentResultListener("requestKey") { requestKey, bundle ->
            when (bundle.getInt("JOGADORES")) {
                2 -> {
                    jogadores = 2
                    Log.d("jogadores", "$jogadores")
                }
                3 -> {
                    jogadores = 3
                    Log.d("jogadores", "$jogadores")
                }
                else -> {}
            }
        }
               return fragmentMainBinding.root
    }


    override fun onClick(view: View) {
        var jogada = -1

        when (view.getId()) {
            R.id.papelBt -> {
                jogada = 0
                fragmentMainBinding.jogadaIv.setImageResource(R.mipmap.papel)
            }
            R.id.tesouraBt -> {
                jogada = 1
                fragmentMainBinding.jogadaIv.setImageResource(R.mipmap.tesoura)
            }
            R.id.pedraBt -> {
                jogada = 2
                fragmentMainBinding.jogadaIv.setImageResource(R.mipmap.pedra)
            }
            else -> {}
        }
        jogarJokenpo(jogada)
    }

    fun jogarJokenpo(jogada: Int) {

        var random = Random(System.currentTimeMillis())
        var jogadaComputador1 = random.nextInt(3)
        var jogadaComputador2 = random.nextInt(3)
        var resultado: String = ""
        fragmentMainBinding.jogadaComp1Iv.visibility = View.VISIBLE

        //Jogada computador 1
        when (jogadaComputador1) {
            0 -> fragmentMainBinding.jogadaComp1Iv.setImageResource(R.mipmap.papel)
            1 -> fragmentMainBinding.jogadaComp1Iv.setImageResource(R.mipmap.tesoura)
            2 -> fragmentMainBinding.jogadaComp1Iv.setImageResource(R.mipmap.pedra)
        }


        //Jogada do computador 2
        when (jogadaComputador2) {
            0 -> fragmentMainBinding.jogadaComp2Iv.setImageResource(R.mipmap.papel)
            1 -> fragmentMainBinding.jogadaComp2Iv.setImageResource(R.mipmap.tesoura)
            2 -> fragmentMainBinding.jogadaComp2Iv.setImageResource(R.mipmap.pedra)
        }

        if (jogadores <= 2) {
            fragmentMainBinding.jogadaComp2Iv.visibility = View.INVISIBLE
            if (jogadaComputador1 == jogada) {
                resultado = "Vocês empataram!"
                fragmentMainBinding.resultadoTv.setTextColor(Color.GREEN)
            } else if ((jogadaComputador1 == 0 && jogada == 1) || (jogadaComputador1 == 1 && jogada == 2) || (jogadaComputador1 == 2 && jogada == 0)) {
                resultado = "Você ganhou! :) "
                fragmentMainBinding.resultadoTv.setTextColor(Color.BLUE)
            } else {
                resultado = "Você Perdeu! :(  "
                fragmentMainBinding.resultadoTv.setTextColor(Color.RED)
            }
            return fragmentMainBinding.resultadoTv.setText(resultado)
        }

        if (jogadores == 3) {
            fragmentMainBinding.jogadaComp2Iv.visibility = View.VISIBLE
            if (((jogadaComputador1 == jogadaComputador2) && (jogadaComputador1 == jogada)) || ((jogadaComputador1 != jogadaComputador2) && (jogadaComputador1 != jogada) && (jogadaComputador2 != jogada))) {
                resultado = "Vocês empataram!"
                fragmentMainBinding.resultadoTv.setTextColor(Color.GREEN)
            } else if ((jogadaComputador1 == 0 && jogada == 1) || (jogadaComputador1 == 1 && jogada == 2) || (jogadaComputador1 == 2 && jogada == 0) && (jogadaComputador1 == jogadaComputador2)) {
                resultado = "Você ganhou! :) "
                fragmentMainBinding.resultadoTv.setTextColor(Color.BLUE)
            } else if ( ((jogada == 0 && jogadaComputador1 == 1) && (jogada == 1 && jogadaComputador1 == 2))
                        || ((jogada == 2 && jogadaComputador1 == 0) && (jogada == jogadaComputador2))
                || ((jogada == jogadaComputador2) && (jogada == 2) && (jogadaComputador1 == 0))
                || ((jogada == jogadaComputador2) && (jogada == 0) && (jogadaComputador1 == 2))
                || ((jogada == jogadaComputador2) && (jogada == 1) && (jogadaComputador1 == 2))
            ) {
                resultado = "Computador 1 ganhou. Você perdeu! :( "
                fragmentMainBinding.resultadoTv.setTextColor(Color.RED)
            } else if (((jogadaComputador1 == jogadaComputador2 && jogadaComputador1 == 1) && (jogadaComputador1 == jogadaComputador2 && jogadaComputador1 == 0)) || ((jogadaComputador1 == jogadaComputador2 && jogadaComputador1 == 0) && (jogadaComputador1 == jogadaComputador2 && jogadaComputador1 == 2)) || ((jogadaComputador1 == jogadaComputador2 && jogadaComputador1 == 2) && (jogadaComputador1 == jogadaComputador2 && jogadaComputador1 == 1))) {
                resultado = " Você perdeu e os outros empataram na vitória! :( "
                fragmentMainBinding.resultadoTv.setTextColor(Color.RED)

            } else if ( ((jogada == jogadaComputador1 && jogadaComputador1 == 1) && (jogada == jogadaComputador1 && jogadaComputador1 == 0))
                || ((jogada == jogadaComputador1 && jogadaComputador1 == 0) && (jogada == jogadaComputador2 && jogadaComputador2 == 2))
                || ((jogada == jogadaComputador1 && jogadaComputador1 == 2) && (jogada == jogadaComputador2 && jogadaComputador2 == 1)) ) {
                resultado = " Você empatou na vitória com PC 1! :) "
                fragmentMainBinding.resultadoTv.setTextColor(Color.BLUE)

            } else if ( ((jogada == jogadaComputador2 && jogadaComputador2 == 1) && (jogada == jogadaComputador2 && jogadaComputador2 == 0))
                || ((jogada == jogadaComputador2 && jogadaComputador2 == 0) && (jogada == jogadaComputador2 && jogadaComputador2 == 2))
                || ((jogada == jogadaComputador2 && jogadaComputador2 == 2) && (jogada == jogadaComputador2 && jogadaComputador2 == 1))) {
                resultado = " Você empatou na vitória com PC 2! :) "
                fragmentMainBinding.resultadoTv.setTextColor(Color.BLUE)
           } else if ( ((jogada == 0 && jogadaComputador2 == 1) || (jogada == 1 && jogadaComputador2 == 2) || (jogada == 2 && jogadaComputador2 == 0) && (jogada == jogadaComputador1))
                        || ((jogada == jogadaComputador1) && (jogada == 2) && (jogadaComputador2 == 0))
                        || ((jogada == jogadaComputador1) && (jogada == 0) && (jogadaComputador2 == 2))
                        || ((jogada == jogadaComputador1) && (jogada == 1) && (jogadaComputador2 == 2)) ){
                resultado = "Computador 2 ganhou. Você perdeu! :( "
                fragmentMainBinding.resultadoTv.setTextColor(Color.RED)
            } else{
                resultado = "Você perdeu! :( "
                fragmentMainBinding.resultadoTv.setTextColor(Color.RED)
            }
            return fragmentMainBinding.resultadoTv.setText(resultado)

        }

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("JOGADORES", jogadores)

    }
}



















package br.edu.ifsp.scl.sdm.pedrapapeltesourafragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResult
import br.edu.ifsp.scl.sdm.pedrapapeltesourafragments.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment() {
    private lateinit var fragmentSettingsBinding:  FragmentSettingsBinding
    private var jogadores = 0



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)


        fragmentSettingsBinding.doisJogadoresRb.setOnClickListener{
            jogadores = 2
        }
        fragmentSettingsBinding.tresJogadoresRb.setOnClickListener{
            jogadores = 3
        }

        fragmentSettingsBinding.salvarBt.setOnClickListener {
            setFragmentResult("requestKey", bundleOf("JOGADORES" to jogadores))
            activity?.supportFragmentManager?.popBackStack()
        }
        fragmentSettingsBinding.fecharFragmentoBt.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        return fragmentSettingsBinding.root


    }


}


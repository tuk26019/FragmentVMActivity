package edu.temple.diceroll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

const val DIE_SIDES = "dIcE_SiDeS"

class DiceFragment : Fragment() {
    private var sides: Int? = null

    // ViewModel declaration
    private lateinit var viewModel: DiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Activity-scoped ViewModel initialization
        viewModel = ViewModelProvider(requireActivity())[DiceViewModel::class.java]
        arguments?.let {
            sides = it.getInt(DIE_SIDES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dice, container, false)
        view.findViewById<Button>(R.id.rollButton).setOnClickListener {
            sides?.let { sides ->
                viewModel.rollDice(sides)
            }
        }

        viewModel.diceRollResult.observe(viewLifecycleOwner, Observer { result ->
            activity?.findViewById<TextView>(R.id.numberDisplay)?.text = result.toString()
        })

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(sides: Int) =
            DiceFragment().apply {
                arguments = Bundle().apply {
                    putInt(DIE_SIDES, sides)
                }
            }
    }
}
package br.com.nibook.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import br.com.nibook.R
import br.com.nibook.data.Contact
import br.com.nibook.databinding.FragmentRegisterBinding
import br.com.nibook.viewmodel.ContactViewModel
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            // Add menu items here
            menuInflater.inflate(R.menu.register_menu, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            // Handle the menu selection
            return when (menuItem.itemId) {
                R.id.action_salvarContato -> {
                    val name = binding.commonLayout.editTextName.text.toString()
                    val surname = binding.commonLayout.editTextSurname.text.toString()
                    val birthday = binding.commonLayout.editTextBirthday.text.toString()
                    val phone = binding.commonLayout.editTextPhone.text.toString()
                    val email = binding.commonLayout.editTextEmail.text.toString()

                    val contact = Contact(0, name, surname, birthday, phone, email)
                    viewModel.insert(contact)
                    Snackbar.make(binding.root, "Contato salvo!", Snackbar.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                    true
                    } else -> false
            }
        }
    }, viewLifecycleOwner, Lifecycle.State.RESUMED)
}



}
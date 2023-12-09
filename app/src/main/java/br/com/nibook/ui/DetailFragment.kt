package br.com.nibook.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.nibook.R
import br.com.nibook.data.Contact
import br.com.nibook.databinding.FragmentDetailBinding
import br.com.nibook.viewmodel.ContactViewModel
import com.google.android.material.snackbar.Snackbar

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    lateinit var contact: Contact
    lateinit var nameEditText: EditText
    lateinit var surnameEditText: EditText
    lateinit var birthdayEditText: EditText
    lateinit var phoneEditText: EditText
    lateinit var emailEditText: EditText
    lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameEditText = binding.common.editTextName
        surnameEditText = binding.common.editTextSurname
        birthdayEditText = binding.common.editTextBirthday
        phoneEditText = binding.common.editTextPhone
        emailEditText = binding.common.editTextEmail
        val idContact = requireArguments().getInt("idContact")
        viewModel.getContact(idContact)
        viewModel.contact.observe(viewLifecycleOwner) { result ->
            result?.let {
                contact = result
                nameEditText.setText(contact.name)
                surnameEditText.setText(contact.surname)
                birthdayEditText.setText(contact.birthday)
                phoneEditText.setText(contact.phone)
                emailEditText.setText(contact.email)
            }
        }

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.detail_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_alterarContato -> {
                        contact.name = nameEditText.text.toString()
                        contact.surname = surnameEditText.text.toString()
                        contact.birthday = birthdayEditText.text.toString()
                        contact.phone = phoneEditText.text.toString()
                        contact.email = emailEditText.text.toString()

                        viewModel.update(contact)
                        Snackbar.make(binding.root, "Contato modificado!", Snackbar.LENGTH_SHORT)
                            .show()
                        findNavController().popBackStack()
                        true
                    }

                    R.id.action_excluirContato -> {
                        viewModel.delete(contact)
                        Snackbar.make(binding.root, "Contato excluído!", Snackbar.LENGTH_SHORT)
                            .show()
                        findNavController().popBackStack()
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}



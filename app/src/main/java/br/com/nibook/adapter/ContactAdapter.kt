package br.com.nibook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.com.nibook.data.Contact
import br.com.nibook.databinding.ContactCellBinding

class ContactAdapter(): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(), Filterable {
     private lateinit var binding: ContactCellBinding
     var contactsList = ArrayList<Contact>()
     var listener: ContactListener?=null
     var contactsListFilterable = ArrayList<Contact>()

    fun updateList(newList: ArrayList<Contact> ){
        contactsList = newList
        contactsListFilterable = contactsList
        notifyDataSetChanged()
    }

    fun setClickListener(listener: ContactListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactViewHolder {
        binding = ContactCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.nameVH.text = contactsList[position].name
        holder.birthdayVH.text = contactsList[position].birthday
    }

    override fun getItemCount(): Int {
        return contactsListFilterable.size
    }

    inner class ContactViewHolder(view:ContactCellBinding): RecyclerView.ViewHolder(view.root)
    {
        val nameVH = view.name
        val birthdayVH = view.birthday
        init {
            view.root.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }
    interface ContactListener
    {
        fun onItemClick(pos: Int)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                if (p0.toString().isEmpty())
                    contactsListFilterable = contactsList
                else {
                    val resultList = ArrayList<Contact>()
                    for (row in contactsList)
                        if (row.name.lowercase().contains(p0.toString().lowercase()))
                            resultList.add(row)
                    contactsListFilterable = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = contactsListFilterable
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                contactsListFilterable = p1?.values as ArrayList<Contact>
                notifyDataSetChanged()
            }
        }
    }

}
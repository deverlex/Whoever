package vn.whoever.views.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.whoever.R;
import vn.whoever.TransConnection.ContactTransaction;
import vn.whoever.adapters.DataAdapter;
import vn.whoever.adapters.OnLoadMoreListener;
import vn.whoever.models.SearchContact;
import vn.whoever.utils.Initgc;

/**
 * Created by spider man on 4/8/2016.
 */
public class AddContactFragment extends Fragment implements Initgc {

    private ImageButton btnBackContactList;
    private ImageButton btnSearchFriends;
    private ImageButton btnOpenChoicePostalCode;
    private TextView textViewPostalCode;
    private RecyclerView recyclerViewSearch;
    private EditText textSearchContact;
    private ProgressDialog progressDialogQuery = null;

    private ContactTransaction contactTransaction;
    private LinearLayoutManager linearLayoutManager;
    private DataAdapter dataAdapter;
    private List<SearchContact> searchContactList;
    private Handler mHandler;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_contact_layout, null);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        btnBackContactList = (ImageButton) view.findViewById(R.id.btnBackContactList);
        btnSearchFriends = (ImageButton) view.findViewById(R.id.btnSearchFiends);
        btnOpenChoicePostalCode = (ImageButton) view.findViewById(R.id.btnOpenChoicePostalCode);
        textViewPostalCode = (TextView) view.findViewById(R.id.textPostalCodeByCountries);
        textSearchContact = (EditText) view.findViewById(R.id.editTextQueryAddFriends);

        recyclerViewSearch = (RecyclerView) view.findViewById(R.id.listSuggestFriendAdd);
        searchContactList = new ArrayList<>();
        mHandler = new Handler();

        recyclerViewSearch.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerViewSearch.setLayoutManager(linearLayoutManager);
        dataAdapter = new DataAdapter(this, searchContactList, recyclerViewSearch);

        recyclerViewSearch.setAdapter(dataAdapter);

        contactTransaction = new ContactTransaction(getActivity());
    }

    @Override
    public void initListener(View view) {
        btnBackContactList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btnSearchFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnOpenChoicePostalCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textViewPostalCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textSearchContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                loadQuerySearch(textSearchContact.getText().toString());
            }
        });

        dataAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                searchContactList.add(null);
                dataAdapter.notifyItemInserted(searchContactList.size() - 1);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        searchContactList.remove(searchContactList.size() - 1);
                        dataAdapter.notifyItemRemoved(searchContactList.size());
                        fetchSearchContact();
                        dataAdapter.setLoaded();
                    }
                }, 2000);
            }
        });
    }

    public void loadQuerySearch(String query) {
        progressDialogQuery = ProgressDialog.show(getActivity(), "", "waiting query...", true);
        contactTransaction.queryContact(query);
        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0; i < 20; ++i) {
                    if(contactTransaction.getSearchContactList().size() > 0) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressDialogQuery.dismiss();
                                searchContactList = contactTransaction.getSearchContactList();
                                for(int i = 0; i< searchContactList.size(); ++i) {
                                    dataAdapter.addItem(searchContactList.get(i));
                                }
                            }
                        });
                        break;
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {}
                }

            }
        }).start();

    }

    public void fetchSearchContact() {

    }

    @Override
    public void onPause() {
        if(progressDialogQuery != null && progressDialogQuery.isShowing()) {
            progressDialogQuery.dismiss();
        }
        super.onPause();
    }

    @Override
    public void initGc() {

    }
}

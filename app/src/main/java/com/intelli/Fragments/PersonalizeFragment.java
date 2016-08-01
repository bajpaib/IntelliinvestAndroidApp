package com.intelli.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.intelli.intelliinvest.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonalizeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonalizeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalizeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private ViewFlipper mViewFlipper;
    private Context mContext;
    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());

    public PersonalizeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalizeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalizeFragment newInstance(String param1, String param2) {
        PersonalizeFragment fragment = new PersonalizeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_personalize, container, false);
        mContext = getActivity();
        setQuestionnaire(rootView);

        return rootView;
    }

    public void setQuestionnaire(View rootView)
    {
        mViewFlipper = (ViewFlipper) rootView.findViewById(R.id.question_flipper);
        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });
    }

    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_left));
//                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out_left));
                    mViewFlipper.showNext();
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_right));
//                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.slide_out_right));
                    mViewFlipper.showPrevious();
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }

    public void showPreviousView(View v)
    {
        mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_right));
//                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.slide_out_right));
        mViewFlipper.showPrevious();
    }

    public void showNextView(View v)
    {
        mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_left));
//                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out_left));
        mViewFlipper.showNext();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

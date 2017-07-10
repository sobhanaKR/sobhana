package com.example.sobhana.first;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<> ( MainActivity.class );

    @Test
    public void mainActivityTest () {
        ViewInteraction editText = onView (
                allOf ( withId ( R.id.skip ), withText ( "skip" ),
                        childAtPosition (
                                childAtPosition (
                                        withId ( android.R.id.content ),
                                        0 ),
                                1 ),
                        isDisplayed ( ) ) );
       editText.check ( matches ( withText ( "skip" ) ) );

        ViewInteraction button = onView (
                allOf ( withId ( R.id.signup ),
                        childAtPosition (
                                childAtPosition (
                                        withId ( android.R.id.content ),
                                        0 ),
                                4 ),
                        isDisplayed ( ) ) );
      button.check ( matches ( isDisplayed ( ) ) );

        ViewInteraction imageView = onView (
                allOf ( withId ( R.id.imageView ),
                        childAtPosition (
                                childAtPosition (
                                        withId ( android.R.id.content ),
                                        0 ),
                                0 ),
                        isDisplayed ( ) ) );
        imageView.check ( matches ( isDisplayed ( ) ) );

        ViewInteraction textView = onView (
                allOf ( withId ( R.id.textView ), withText ( "STORES" ),
                        childAtPosition (
                                childAtPosition (
                                        withId ( android.R.id.content ),
                                        0 ),
                                3 ),
                        isDisplayed ( ) ) );
        textView.check ( matches ( withText ( "STORES" ) ) );

        ViewInteraction button2 = onView (
                allOf ( withId ( R.id.login ),
                        childAtPosition (
                                childAtPosition (
                                        withId ( android.R.id.content ),
                                        0 ),
                                5 ),
                        isDisplayed ( ) ) );
        button2.check ( matches ( isDisplayed ( ) ) );

        ViewInteraction editText2 = onView (
                allOf ( withId ( R.id.skip ), withText ( "skip" ),
                        childAtPosition (
                                childAtPosition (
                                        withId ( android.R.id.content ),
                                        0 ),
                                1 ),
                        isDisplayed ( ) ) );
        editText2.check ( matches ( withText ( "skip" ) ) );

    }

    private static Matcher<View> childAtPosition (
            final Matcher<View> parentMatcher, final int position ) {

        return new TypeSafeMatcher<View> ( ) {
            @Override
            public void describeTo ( Description description ) {
                description.appendText ( "Child at position " + position + " in parent " );
                parentMatcher.describeTo ( description );
            }

            @Override
            public boolean matchesSafely ( View view ) {
                ViewParent parent = view.getParent ( );
                return parent instanceof ViewGroup && parentMatcher.matches ( parent )
                        && view.equals ( ((ViewGroup) parent).getChildAt ( position ) );
            }
        };
    }
}

package com.example.demo.validationMessages;

public final class ValidationMessages {
    /**
     *default constructor.
    */
    private ValidationMessages() {
    }
    /**
     * message when email blank.
    */
    public static final String EMAIL_NOTBLANK = "Email Id is required";
    /**
     *message when email pattern wrong.
    */
    public static final String EMAIL_PATTERN = "Not a valid email";
    /**
     *message when password blank.
    */
    public static final String PASSWORD_NOTBLANK = "Password is required";
    /**
     *message when password pattern wrong.
    */
    public static final String PASSWORD_PATTERN = "Password must be at least 8 "
                 + "characters long and "
                 + "contain at least one digit, one lowercase letter,"
                 + " one uppercase letter, "
                 + "one special character, and no whitespace";
    /**
     *when category id null.
    */
    public static final String CATEGORY_NOTNULL = "Category Id is required";
    /**
     *category name is blank.
    */
    public static final String CATEGORY_NOTBLANK = "Category name is required";
    /**
     *category name is blank.
    */
    public static final String CATEGORYDESCRIPTION_NOTBLANK =
            "Category description is required";
    /**
     *quiz id is null.
    */
    public static final String QUIZID_NOTNULL = "Quiz Id is required";
    /**
     *quiz name is blank.
    */
    public static final String QUIZNAME_NOTBLANK = "Quiz Name is required";
    /**
     *quiz name is blank.
    */
    public static final String QUIZDESCRIPTION_NOTBLANK =
            "Quiz Description is required";
    /**
     *time limit is blank.
    */
    public static final String TIMELIMIT_NOTBLANK = "Time Limit is required";
    /**
     *question is blank.
    */
    public static final String QUESTION_NOTBLANK = "Question is required";
    /**
     *optionA is blank.
    */
    public static final String OPTION1_NOTBLANK = "Option1 is required";
    /**
     *optionB is blank.
    */
    public static final String OPTION2_NOTBLANK = "Option2 is required";
    /**
     *optionC is blank.
    */
    public static final String OPTION3_NOTBLANK = "Option3 is required";
    /**
     *optionD is blank.
    */
    public static final String OPTION4_NOTBLANK = "Option4 is required";
    /**
     *correct answer is blank.
    */
    public static final String CORRECTANSWER_NOTBLANK = "Correct "
            + "Answer is required";
    /**
     *student id is null.
    */
    public static final String STUDENTID_NOTNULL = "Student Id is required";
    /**
     *marks obtained is null.
    */
    public static final String MARKSOBTAINED_NOTNULL = "Marks Obtained "
            + "is required";
    /**
     *total questions is null.
    */
    public static final String TOTALQUESTIONS_NOTNULL = "Total Questions "
            + "is required";
    /**
     *total marks is null.
    */
    public static final String TOTALMARKS_NOTNULL = "Total Marks is required";
    /**
     *attempted questions is null.
    */
    public static final String ATTEMPTEDQUESTIONS_NOTNULL = "Attempted "
            + "Questions is required";
    /**
     *first name is blank.
    */
    public static final String NAME_NOTBLANK = "Name is required";
    /**
     *date of birth is blank.
    */
    public static final String DOB_NOTBLANK = "Date of Birth is required";

    /**
     *gender is blank.
    */
    public static final String GENDER_NOTBLANK = "Gender is required";
    /**
     *phone number is blank.
    */
    public static final String PHONENUMBER_NOTBLANK = "Gender is required";
    /**
     *phone number is blank.
    */
    public static final String PHONENUMBER_PATTERN =
            "Phone number must contain 10 digits";
    /**
     *role is blank.
    */
    public static final String ROLE_NOTBLANK = "Role is required";
    /**
     *role is blank.
    */
    public static final String ID_NOTBLANK = "Id required";

}

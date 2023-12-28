public class BuilderPattern {
    private String firstname;
    private String lastname;
    private String email;
    private String phone;

    public BuilderPattern(String firtname, String lastname, String email, String phone) {
        this.firstname = firtname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }

    public String getLastname() {
        return lastname;
    }

//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

//    public void setPhone(String phone) {
//        this.phone = phone;
//    }

    public static void example() {
        String name = null;
    }

    @Override
    public String toString() {
        return "BuilderPattern{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static final class Builder {
        private String firstname;
        private String lastname;
        private String email;
        private String phone;

        private Builder() {
        }

        public static Builder aBuilderPattern() {
            return new Builder();
        }

        public Builder withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder withLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public BuilderPattern build() {
            BuilderPattern builderPattern = new BuilderPattern(null, lastname, email, phone);
            builderPattern.firstname = this.firstname;
            return builderPattern;
        }
    }
}

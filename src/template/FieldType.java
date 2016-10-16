package template;


/**
 * Created by zhanglin on 16-10-12.
 */
public enum FieldType {
    STRING("String", CodeTemplate.editTextXmlName, new IValueFormater() {
        @Override
        public String formater() {
            return "#{value}";
        }

        @Override
        public String deFormate() {
            return "#{value}";
        }
    }),
    INT("int", CodeTemplate.editNumberXmlName, new IValueFormater() {
        @Override
        public String formater() {
            return "String.valueOf(#{value})";
        }

        @Override
        public String deFormate() {
            return "Integer.valueOf(#{value})";
        }
    }),
    FLOAT("float", CodeTemplate.editNumberXmlName, new IValueFormater() {
        @Override
        public String formater() {
            return "String.valueOf(#{value})";
        }

        @Override
        public String deFormate() {
            return "Float.valueOf(#{value})";
        }
    }),
    DOUBLE("double", CodeTemplate.editNumberXmlName, new IValueFormater() {
        @Override
        public String formater() {
            return "String.valueOf(#{value})";
        }

        @Override
        public String deFormate() {
            return "Double.valueOf(#{value})";
        }
    }),
    EMAIL("String", CodeTemplate.editEmailXmlName, new IValueFormater() {
        @Override
        public String formater() {
            return "#{value}";
        }

        @Override
        public String deFormate() {
            return "#{value}";
        }
    }),
    NAME("String", CodeTemplate.editTextXmlName, new IValueFormater() {
        @Override
        public String formater() {
            return "#{value}";
        }

        @Override
        public String deFormate() {
            return "#{value}";
        }
    }),
    PASSWORD("String", CodeTemplate.editPasswordXmlName, new IValueFormater() {
        @Override
        public String formater() {
            return "#{value}";
        }

        @Override
        public String deFormate() {
            return "#{value}";
        }
    });

    public String className;
    public String xmlEditModel;
    public IValueFormater formater;

    FieldType(String classname, String xmlEditModel, IValueFormater formater) {
        this.className = classname;
        this.xmlEditModel = xmlEditModel;
        this.formater = formater;
    }

    /**
     * format string to work for codes
     */
    public interface IValueFormater {
        String formater();

        String deFormate();
    }


}

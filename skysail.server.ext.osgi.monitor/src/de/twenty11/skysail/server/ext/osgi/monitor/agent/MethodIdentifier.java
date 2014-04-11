package de.twenty11.skysail.server.ext.osgi.monitor.agent;

public class MethodIdentifier { // implements ClassInstrumentation {

    private String methodName;
    private String signature;
    private String classname;
    
    public MethodIdentifier() {
		// TODO needed for jackson
	}

    public MethodIdentifier(String classname, String methodName, String signature) {
        this.classname = classname;
        this.methodName = methodName;
        this.signature = signature;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(classname).append(": ").append(methodName).append("#").append(signature);
        return sb.toString();
    }
    
    public String getMethodName() {
		return methodName;
	}
    
    public String getSignature() {
		return signature;
	}
    
    public String getClassname() {
		return classname;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classname == null) ? 0 : classname.hashCode());
        result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
        result = prime * result + ((signature == null) ? 0 : signature.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MethodIdentifier other = (MethodIdentifier) obj;
        if (classname == null) {
            if (other.classname != null)
                return false;
        } else if (!classname.equals(other.classname))
            return false;
        if (methodName == null) {
            if (other.methodName != null)
                return false;
        } else if (!methodName.equals(other.methodName))
            return false;
        if (signature == null) {
            if (other.signature != null)
                return false;
        } else if (!signature.equals(other.signature))
            return false;
        return true;
    }

}

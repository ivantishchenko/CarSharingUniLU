package lu.tishchenko.ejb.entity.helper;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by Ivan on 04.01.2017.
 */
@MappedSuperclass
public abstract class AbstractDBObject implements Serializable {
    private Long id;
    private String name;
    private Date created;
    private Date deleted;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", unique=true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "created")
    @Version
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date jpaVersion) {
        this.created = jpaVersion;
    }

    @Column(name = "deleted")
    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    @Transient
    @XmlTransient
    public Object getMyClone() {
        try {
            Class thisClass = this.getClass();
            BeanInfo bi = Introspector.getBeanInfo(thisClass);
            Object cloneObj = thisClass.newInstance();
            PropertyDescriptor[] pds = bi.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                Method readMethod = pd.getReadMethod();
                Method writeMethod = pd.getWriteMethod();
                if (readMethod != null && writeMethod != null) {
                    Object value = readMethod.invoke(this);
                    writeMethod.invoke(cloneObj, value);
                }
            }
            return cloneObj;
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractDBObject that = (AbstractDBObject) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        return deleted != null ? deleted.equals(that.deleted) : that.deleted == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        return result;
    }
}

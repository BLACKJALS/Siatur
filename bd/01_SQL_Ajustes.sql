ALTER TABLE public.rol
  ADD COLUMN rol_editable boolean NOT NULL DEFAULT true;
COMMENT ON COLUMN public.rol.rol_editable IS 'Campo que indica si el registro es editable por el usuario';
